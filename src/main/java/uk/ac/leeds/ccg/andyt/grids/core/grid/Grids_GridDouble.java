/**
 * Version 1.0 is to handle single variable 2DSquareCelled raster data.
 * Copyright (C) 2005 Andy Turner, CCG, University of Leeds, UK.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 */
package uk.ac.leeds.ccg.andyt.grids.core.grid;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_StaticIO;
import uk.ac.leeds.ccg.andyt.grids.core.Grids_2D_ID_int;
import uk.ac.leeds.ccg.andyt.grids.core.Grids_2D_ID_long;
import uk.ac.leeds.ccg.andyt.grids.core.Grids_Dimensions;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_AbstractGridChunkDouble;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_AbstractGridChunkDoubleFactory;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_AbstractGridChunk;
import uk.ac.leeds.ccg.andyt.grids.core.Grids_Environment;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_AbstractGridChunkInt;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_GridChunkDouble;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_GridChunkDoubleArray;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_GridChunkDoubleMap;
import uk.ac.leeds.ccg.andyt.grids.core.grid.statistics.Grids_GridDoubleStatistics;
import uk.ac.leeds.ccg.andyt.grids.core.grid.statistics.Grids_GridDoubleStatisticsNotUpdated;
import uk.ac.leeds.ccg.andyt.grids.core.grid.statistics.Grids_GridIntStatisticsNotUpdated;
import uk.ac.leeds.ccg.andyt.grids.io.Grids_ESRIAsciiGridImporter;
import uk.ac.leeds.ccg.andyt.grids.io.Grids_ESRIAsciiGridImporter.Grids_ESRIAsciiGridHeader;
import uk.ac.leeds.ccg.andyt.grids.process.Grids_Processor;
import uk.ac.leeds.ccg.andyt.grids.utilities.Grids_Utilities;

/**
 * A class for representing grids of double precision values.
 *
 * @see Grids_AbstractGridNumber
 */
public class Grids_GridDouble
        extends Grids_AbstractGridNumber
        implements Serializable {

    /**
     * For storing the NODATA value of the grid, which by default is
     * -Double.MAX_VALUE. N.B. Double.NaN, Double.POSITIVE_INFINITY or
     * Double.NEGATIVE_INFINITY should not be used. N.B. Care should be taken so
     * that NoDataValue is not a data value.
     */
    protected double NoDataValue = -Double.MAX_VALUE;

    /**
     * A reference to the grid Statistics Object.
     */
    protected Grids_GridDoubleStatistics Statistics;

    protected Grids_GridDouble() {
    }

    /**
     * Creates a new Grids_GridDouble. Warning!! Concurrent modification may
     * occur if directory is in use.
     *
     * @param directory The directory to be used for swapping.
     * @param gridFile The directory containing the File named "thisFile" that
     * the ois was constructed from.
     * @param ois The ObjectInputStream used in first attempt to construct this.
     * @param ge
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    protected Grids_GridDouble(
            File directory,
            File gridFile,
            ObjectInputStream ois,
            Grids_Environment ge,
            boolean hoome) {
        super(ge, directory);
        init(
                gridFile,
                ois,
                hoome);
    }

    /**
     * Creates a new Grids_GridDouble with each cell value equal to NoDataValue
     * and all chunks of the same type.
     *
     * @param gs The AbstractGridStatistics to accompany this.
     * @param directory The directory to be used for swapping.
     * @param chunkFactory The Grids_AbstractGridChunkDoubleFactory preferred
     * for creating chunks.
     * @param chunkNRows The number of rows of cells in any chunk.
     * @param chunkNCols The number of columns of cells in any chunk.
     * @param nRows The number of rows of cells.
     * @param nCols The number of columns of cells.
     * @param dimensions The cellsize, xmin, ymin, xmax and ymax.
     * @param noDataValue The NoDataValue.
     * @param ge
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    protected Grids_GridDouble(
            Grids_GridDoubleStatistics gs,
            File directory,
            Grids_AbstractGridChunkDoubleFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long nRows,
            long nCols,
            Grids_Dimensions dimensions,
            double noDataValue,
            Grids_Environment ge,
            boolean hoome) {
        super(ge, directory);
        init(gs,
                directory,
                chunkFactory,
                chunkNRows,
                chunkNCols,
                nRows,
                nCols,
                dimensions,
                noDataValue,
                hoome);
    }

    /**
     * Creates a new Grids_GridDouble based on values in grid.
     *
     * @param statistics The AbstractGridStatistics to accompany this.
     * @param directory The File _Directory to be used for swapping.
     * @param grid The Grids_AbstractGridNumber from which this is to be
     * constructed.
     * @param chunkFactory The Grids_AbstractGridChunkDoubleFactory preferred to
     * construct chunks of this.
     * @param chunkNRows The number of rows of cells in any chunk.
     * @param chunkNCols The number of columns of cells in any chunk.
     * @param startRowIndex The Grid2DSquareCell row index which is the bottom
     * most row of this.
     * @param startColIndex The Grid2DSquareCell column index which is the left
     * most column of this.
     * @param endRowIndex The Grid2DSquareCell row index which is the top most
     * row of this.
     * @param endColIndex The Grid2DSquareCell column index which is the right
     * most column of this.
     * @param noDataValue The NoDataValue for this.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    protected Grids_GridDouble(
            Grids_GridDoubleStatistics statistics,
            File directory,
            Grids_AbstractGridNumber grid,
            Grids_AbstractGridChunkDoubleFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRowIndex,
            long startColIndex,
            long endRowIndex,
            long endColIndex,
            double noDataValue,
            boolean hoome) {
        super(grid.ge, directory);
        init(statistics, grid, chunkFactory, chunkNRows, chunkNCols,
                startRowIndex, startColIndex, endRowIndex, endColIndex,
                noDataValue, hoome);
    }

    /**
     * Creates a new Grids_GridDouble with values obtained from gridFile.
     * Currently gridFile must be a directory of a Grids_GridDouble or
     * Grids_GridInt or an ESRI Asciigrid format file with a filename ending in
     * ".asc" or ".txt".
     *
     * @param gs The statistics to accompany this grid.
     * @param directory The directory to be used for storing data about this
     * grid.
     * @param gridFile Either a directory, or a formatted File with a specific
     * extension containing the data for this.
     * @param chunkFactory The factory preferred to construct chunks of this.
     * @param chunkNRows
     * @param chunkNCols
     * @param startRowIndex The row of the input that will be the bottom most
     * row of this.
     * @param startColIndex The column of the input that will be the left most
     * column of this.
     * @param endRowIndex The row of the input that will be the top most row of
     * this.
     * @param endColIndex The column of the input that will be the right most
     * column of this.
     * @param noDataValue The NoDataValue for this.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     * @param ge
     */
    protected Grids_GridDouble(
            Grids_GridDoubleStatistics gs,
            File directory,
            File gridFile,
            Grids_AbstractGridChunkDoubleFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRowIndex,
            long startColIndex,
            long endRowIndex,
            long endColIndex,
            double noDataValue,
            Grids_Environment ge,
            boolean hoome) {
        super(ge, directory);
        init(gs,
                gridFile,
                chunkFactory,
                chunkNRows,
                chunkNCols,
                startRowIndex,
                startColIndex,
                endRowIndex,
                endColIndex,
                noDataValue,
                hoome);
    }

    /**
     * Creates a new Grids_GridDouble with values obtained from gridFile.
     * Currently gridFile must be a directory of a Grids_GridDouble or
     * Grids_GridInt or an ESRI Asciigrid format file with a filename ending in
     * ".asc" or ".txt".
     *
     * @param ge
     * @param directory The directory to be used for storing data about this
     * grid.
     * @param gridFile Either a directory, or a formatted File with a specific
     * extension containing the data for this.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    protected Grids_GridDouble(
            Grids_Environment ge,
            File directory,
            File gridFile,
            boolean hoome) {
        super(ge, directory);
        init(new Grids_GridDoubleStatisticsNotUpdated(ge),
                gridFile,
                hoome);
    }

    /**
     * @return a string description of the instance. Basically the values of
     * each field.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    @Override
    public String toString(boolean hoome) {
        try {
            String result = getClass().getName()
                    + "(NoDataValue(" + NoDataValue + "), "
                    + super.toString(hoome) + ")";
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                if (!ge.swapChunk(ge.HOOMEF)) {
                    throw e;
                }
                ge.initMemoryReserve(hoome);
                return toString(
                        hoome);
            } else {
                throw e;
            }
        }
    }

    private void init(
            Grids_GridDouble g,
            boolean initTransientFields) {
        NoDataValue = g.NoDataValue;
        super.init(g);
        if (initTransientFields) {
            ChunkIDChunkMap = g.ChunkIDChunkMap;
            ChunkIDsofChunksWorthSwapping = g.ChunkIDsofChunksWorthSwapping;
            // Set the reference to this in the Grid Statistics
            Statistics.init(this);
        }
        ge.addGrid(this);
    }

    private void init(
            File file,
            ObjectInputStream ois,
            boolean hoome) {
        try {
            File thisFile = new File(
                    file,
                    "thisFile");
            try {
                boolean initTransientFields = false;
                init((Grids_GridDouble) ois.readObject(),
                        initTransientFields);
                ois.close();
                // Set the reference to this in the Grid Chunks
                initChunks(file);
                Iterator<Grids_AbstractGridChunk> chunkIterator;
                chunkIterator = ChunkIDChunkMap.values().iterator();
                while (chunkIterator.hasNext()) {
                    Grids_AbstractGridChunk chunk = chunkIterator.next();
                    chunk.setGrid(this);
                }
            } catch (ClassCastException e) {
                try {
                    ois.close();
                    ois = Generic_StaticIO.getObjectInputStream(thisFile);
                    // If the object is a Grids_GridInt
                    Grids_Processor gp;
                    gp = ge.getProcessor();
                    Grids_GridIntFactory gif;
                    gif = new Grids_GridIntFactory(
                            ge,
                            gp.GridIntFactory.Directory,
                            gp.GridChunkIntFactory,
                            gp.DefaultGridChunkIntFactory,
                            Integer.MIN_VALUE,
                            ChunkNRows,
                            ChunkNCols,
                            Dimensions,
                            new Grids_GridIntStatisticsNotUpdated(ge));
                    Grids_GridInt gridInt;
                    gridInt = (Grids_GridInt) gif.create(
                            Directory,
                            file,
                            ois,
                            hoome);
                    Grids_GridDoubleFactory gdf = new Grids_GridDoubleFactory(
                            ge,
                            gp.GridDoubleFactory.Directory,
                            gp.GridChunkDoubleFactory,
                            gp.DefaultGridChunkDoubleFactory,
                            gridInt.NoDataValue,
                            gridInt.ChunkNRows,
                            gridInt.ChunkNCols,
                            gridInt.Dimensions,
                            new Grids_GridDoubleStatisticsNotUpdated(ge));
                    Grids_GridDouble gridDouble = (Grids_GridDouble) gdf.create(gridInt);
                    boolean initTransientFields = false;
                    init(gridDouble, initTransientFields);
                    initChunks(file);
                } catch (IOException ioe) {
                    //ioe.printStackTrace();
                    System.err.println(ioe.getLocalizedMessage());
                }
            } catch (ClassNotFoundException | IOException e) {
                //ioe.printStackTrace();
                System.err.println(e.getLocalizedMessage());
            }
            //ioe.printStackTrace();
            // Set the reference to this in the Grid Statistics
            this.getStatistics().init(this);
            ge.addGrid(this);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                if (ge.swapChunks_Account(false) < 1L) {
                    throw e;
                }
                init(file,
                        ois,
                        hoome);
            } else {
                throw e;
            }
        }
    }

    private void init(
            Grids_GridDoubleStatistics statistics,
            File directory,
            Grids_AbstractGridChunkDoubleFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long nRows,
            long nCols,
            Grids_Dimensions dimensions,
            double noDataValue,
            boolean hoome) {
        try {
            ge.checkAndMaybeFreeMemory(hoome);
            Directory = directory;
            Statistics = statistics;
            Statistics.init(this);
            Directory = directory;
            ChunkNRows = chunkNRows;
            ChunkNCols = chunkNCols;
            NRows = nRows;
            NCols = nCols;
            Dimensions = dimensions;
            initNoDataValue(noDataValue);
            Name = directory.getName();
            initNChunkRows();
            initNChunkCols();
            ChunkIDChunkMap = new TreeMap<>();
            ChunkIDsofChunksWorthSwapping = new HashSet<>();
            int chunkRow;
            int chunkCol;
            boolean isLoadedChunk = false;
            Grids_2D_ID_int chunkID;
            Grids_AbstractGridChunkDouble chunk;
            for (chunkRow = 0; chunkRow < NChunkRows; chunkRow++) {
                for (chunkCol = 0; chunkCol < NChunkCols; chunkCol++) {
                    do {
                        try {
                            ge.checkAndMaybeFreeMemory(hoome);
                            // Try to load chunk.
                            chunkID = new Grids_2D_ID_int(
                                    chunkRow,
                                    chunkCol);
                            chunk = chunkFactory.create(
                                    this,
                                    chunkID);
                            ChunkIDChunkMap.put(
                                    chunkID,
                                    chunk);
                            if (!(chunk instanceof Grids_GridChunkDouble)) {
                                ChunkIDsofChunksWorthSwapping.add(chunkID);
                            }
                            isLoadedChunk = true;
                            ge.checkAndMaybeFreeMemory(hoome);
                        } catch (OutOfMemoryError e) {
                            if (hoome) {
                                ge.clearMemoryReserve();
                                freeSomeMemoryAndResetReserve(chunkRow, chunkCol, e);
                            } else {
                                throw e;
                            }
                        }
                    } while (!isLoadedChunk);
                    isLoadedChunk = false;
                    //loadedChunkCount++;
                }
                System.out.println("Done chunkRow " + chunkRow + " out of " + NChunkRows);
            }
            ge.addGrid(this);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                freeSomeMemoryAndResetReserve(e);
                init(statistics,
                        directory,
                        chunkFactory,
                        chunkNRows,
                        chunkNCols,
                        nRows,
                        nCols,
                        dimensions,
                        noDataValue,
                        hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     *
     * @param statistics
     * @param g
     * @param chunkFactory
     * @param chunkNRows
     * @param chunkNCols
     * @param startRow
     * @param startCol
     * @param endRow
     * @param endCol
     * @param noDataValue
     * @param hoome
     */
    private void init(
            Grids_GridDoubleStatistics statistics,
            Grids_AbstractGridNumber g,
            Grids_AbstractGridChunkDoubleFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRow,
            long startCol,
            long endRow,
            long endCol,
            double noDataValue,
             boolean hoome
    ) {
        try {
            ge.checkAndMaybeFreeMemory();
            Statistics = statistics;
            Statistics.init(this);
            ChunkNRows = chunkNRows;
            ChunkNCols = chunkNCols;
            NRows = endRow - startRow + 1L;
            NCols = endCol - startCol + 1L;
            NoDataValue = noDataValue;
            Name = Directory.getName();
            initNChunkRows();
            initNChunkCols();
            ChunkIDChunkMap = new TreeMap<>();
            ChunkIDsofChunksWorthSwapping = new HashSet<>();
            initDimensions(g, startRow, startCol, hoome);
            int gChunkRow;
            int gChunkCol;
            int chunkRow;
            int chunkCol;
            boolean isLoadedChunk = false;
            int cellRow;
            int cellCol;
            long row;
            long col;
            long gRow;
            long gCol;
            Grids_2D_ID_int chunkID;
            Grids_2D_ID_int gChunkID;
            Grids_AbstractGridChunkDouble chunk;
            int gChunkNRows;
            int gChunkNCols;
            int startChunkRow;
            startChunkRow = g.getChunkRow(startRow);
            int endChunkRow;
            endChunkRow = g.getChunkRow(endRow);
            int nChunkRows;
            nChunkRows = endChunkRow - startChunkRow + 1;
            int startChunkCol;
            startChunkCol = g.getChunkCol(startCol);
            int endChunkCol;
            endChunkCol = g.getChunkCol(endCol);
            if (g instanceof Grids_GridDouble) {
                Grids_GridDouble grid = (Grids_GridDouble) g;
                Grids_AbstractGridChunkDouble gChunk;
                double gNoDataValue = grid.getNoDataValue();
                double gValue;
                for (gChunkRow = startChunkRow; gChunkRow <= endChunkRow; gChunkRow++) {
                    gChunkNRows = g.getChunkNRows(gChunkRow);
                    for (gChunkCol = startChunkCol; gChunkCol <= endChunkCol; gChunkCol++) {
                        do {
                            try {
                                // Try to load chunk.
                                gChunkID = new Grids_2D_ID_int(gChunkRow, gChunkCol);
                                ge.addToNotToSwap(g, gChunkID);
                                ge.checkAndMaybeFreeMemory();
                                gChunk = ((Grids_GridDouble) g).getGridChunk(gChunkID);
                                gChunkNCols = g.getChunkNCols(gChunkCol);
                                for (cellRow = 0; cellRow < gChunkNRows; cellRow++) {
                                    //gRow = g.getRow(gChunkRow, cellRow, gChunkID, hoome);
                                    gRow = g.getRow(gChunkRow, cellRow);
                                    row = gRow - startRow;
                                    chunkRow = getChunkRow(row);
                                    if (gRow >= startRow && gRow <= endRow) {
                                        for (cellCol = 0; cellCol < gChunkNCols; cellCol++) {
                                            //gCol = g.getCol(gChunkCol, cellCol, gChunkID, hoome);
                                            gCol = g.getCol(gChunkCol, cellCol);
                                            col = gCol - startCol;
                                            chunkCol = getChunkCol(col);
                                            if (gCol >= startCol && gCol <= endCol) {
                                                // Initialise chunk if it does not exist
                                                // This is here rather than where chunkID is
                                                // initialised as there may not be a chunk for 
                                                // the chunkID.
                                                if (isInGrid(row, col)) {
                                                    chunkID = new Grids_2D_ID_int(chunkRow, chunkCol);
                                                    //ge.addToNotToSwap(this, chunkID);
                                                    if (!ChunkIDChunkMap.containsKey(chunkID)) {
                                                        chunk = chunkFactory.create(this, chunkID);
                                                        ChunkIDChunkMap.put(chunkID, chunk);
                                                        if (!(chunk instanceof Grids_GridChunkDouble)) {
                                                            ChunkIDsofChunksWorthSwapping.add(chunkID);
                                                        }
                                                    } else {
                                                        chunk = (Grids_AbstractGridChunkDouble) ChunkIDChunkMap.get(chunkID);
                                                    }
                                                    gValue = grid.getCell(gChunk, cellRow, cellCol);
                                                    // Initialise value
                                                    if (gValue == gNoDataValue) {
                                                        initCell(chunk, chunkID, row, col, noDataValue);
                                                    } else {
                                                        if (!Double.isNaN(gValue) && Double.isFinite(gValue)) {
                                                            initCell(chunk, chunkID, row, col, gValue);
                                                        } else {
                                                            initCell(chunk, chunkID, row, col, noDataValue);
                                                        }
                                                    }
                                                    //ge.removeFromNotToSwap(this, chunkID);
                                                }
                                            }
                                        }
                                    }
                                }
                                isLoadedChunk = true;
                                ge.removeFromNotToSwap(g, gChunkID);
                            } catch (OutOfMemoryError e) {
                                if (hoome) {
                                    ge.clearMemoryReserve();
                                    freeSomeMemoryAndResetReserve(e);
                                    chunkID = new Grids_2D_ID_int(gChunkRow, gChunkCol);
                                    if (ge.swapChunksExcept_Account(this, chunkID, false) < 1L) { // Should also not swap out the chunk of grid thats values are being used to initialise this.
                                        throw e;
                                    }
                                    ge.initMemoryReserve(this, chunkID, hoome);
                                } else {
                                    throw e;
                                }
                            }
                        } while (!isLoadedChunk);
                        isLoadedChunk = false;
                        //loadedChunkCount++;
                        //cci1 = _ChunkColIndex;
                    }
                    System.out.println("Done chunkRow " + gChunkRow + " out of " + nChunkRows);
                }
            } else {
                Grids_GridInt grid = (Grids_GridInt) g;
                Grids_AbstractGridChunkInt gChunk;
                int gNoDataValue = grid.getNoDataValue();
                int gValue;
                for (gChunkRow = startChunkRow; gChunkRow <= endChunkRow; gChunkRow++) {
                    gChunkNRows = g.getChunkNRows(gChunkRow);
                    for (gChunkCol = startChunkCol; gChunkCol <= endChunkCol; gChunkCol++) {
                        do {
                            try {
                                // Try to load chunk.
                                gChunkID = new Grids_2D_ID_int(gChunkRow, gChunkCol);
                                ge.addToNotToSwap(g, gChunkID);
                                ge.checkAndMaybeFreeMemory();
                                gChunk = ((Grids_GridInt) g).getGridChunk(gChunkID);
                                gChunkNCols = g.getChunkNCols(gChunkCol);
                                for (cellRow = 0; cellRow < gChunkNRows; cellRow++) {
                                    //gRow = g.getRow(gChunkRow, cellRow, gChunkID, hoome);
                                    gRow = g.getRow(gChunkRow, cellRow);
                                    row = gRow - startRow;
                                    chunkRow = getChunkRow(row);
                                    if (gRow >= startRow && gRow <= endRow) {
                                        for (cellCol = 0; cellCol < gChunkNCols; cellCol++) {
                                            //gCol = g.getCol(gChunkCol, cellCol, gChunkID, hoome);
                                            gCol = g.getCol(gChunkCol, cellCol);
                                            col = gCol - startCol;
                                            chunkCol = getChunkCol(col);
                                            if (gCol >= startCol && gCol <= endCol) {
                                                // Initialise chunk if it does not exist
                                                // This is here rather than where chunkID is
                                                // initialised as there may not be a chunk for 
                                                // the chunkID.
                                                if (isInGrid(row, col)) {
                                                    chunkID = new Grids_2D_ID_int(chunkRow, chunkCol);
                                                    ge.addToNotToSwap(this, chunkID);
                                                    if (!ChunkIDChunkMap.containsKey(chunkID)) {
                                                        chunk = chunkFactory.create(this, chunkID);
                                                        ChunkIDChunkMap.put(
                                                                chunkID,
                                                                chunk);
                                                        if (!(chunk instanceof Grids_GridChunkDouble)) {
                                                            ChunkIDsofChunksWorthSwapping.add(chunkID);
                                                        }
                                                    } else {
                                                        chunk = (Grids_AbstractGridChunkDouble) ChunkIDChunkMap.get(chunkID);
                                                    }
                                                    gValue = grid.getCell(gChunk, cellRow, cellCol);
                                                    // Initialise value
                                                    if (gValue == gNoDataValue) {
                                                        initCell(chunk, chunkID, row, col, noDataValue);
                                                    } else {
                                                        if (!Double.isNaN(gValue) && Double.isFinite(gValue)) {
                                                            initCell(chunk, chunkID, row, col, gValue);
                                                        } else {
                                                            initCell(chunk, chunkID, row, col, noDataValue);
                                                        }
                                                    }
                                                    ge.removeFromNotToSwap(this, chunkID);
                                                }
                                            }
                                        }
                                    }
                                }
                                isLoadedChunk = true;
                                ge.removeFromNotToSwap(g, gChunkID);
                                ge.checkAndMaybeFreeMemory(hoome);
                            } catch (OutOfMemoryError e) {
                                if (hoome) {
                                    ge.clearMemoryReserve();
                                    chunkID = new Grids_2D_ID_int(
                                            gChunkRow,
                                            gChunkCol);
                                    if (ge.swapChunksExcept_Account(this, chunkID, false) < 1L) { // Should also not swap out the chunk of grid thats values are being used to initialise this.
                                        throw e;
                                    }
                                    ge.initMemoryReserve(this, chunkID, hoome);
                                } else {
                                    throw e;
                                }
                            }
                        } while (!isLoadedChunk);
                        isLoadedChunk = false;
                    }
                    System.out.println("Done chunkRow " + gChunkRow + " out of " + nChunkRows);
                }
            }
            ge.addGrid(this);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                if (ge.swapChunks_Account(false) < 1) {
                    throw e;
                }
                ge.initMemoryReserve(hoome);
                init(statistics, g, chunkFactory, chunkNRows, chunkNCols,
                        startRow, startCol, endRow, endCol, noDataValue, hoome);
            } else {
                throw e;
            }
        }
    }

    private void init(
            Grids_GridDoubleStatistics statistics,
            File gridFile,
            Grids_AbstractGridChunkDoubleFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRowIndex,
            long startColIndex,
            long endRowIndex,
            long endColIndex,
            double noDataValue,
            boolean hoome) {
        ge.checkAndMaybeFreeMemory(hoome);
        Statistics = statistics;
        Statistics.init(this);
        // Set to report every 10%
        int reportN;
        reportN = (int) (endRowIndex - startRowIndex) / 10;
        if (gridFile.isDirectory()) {
            if (true) {
                Grids_Processor gp;
                gp = ge.getProcessor();
                Grids_GridDoubleFactory gf;
                gf = new Grids_GridDoubleFactory(
                        ge,
                        gp.GridDoubleFactory.Directory,
                        gp.GridChunkDoubleFactory,
                        gp.DefaultGridChunkDoubleFactory,
                        noDataValue,
                        chunkNRows,
                        chunkNCols,
                        null,
                        statistics);
                File thisFile = new File(
                        gridFile,
                        "thisFile");
                ObjectInputStream ois;
                ois = Generic_StaticIO.getObjectInputStream(thisFile);
                Grids_GridDouble g;
                g = (Grids_GridDouble) gf.create(
                        Directory,
                        thisFile,
                        ois,
                        hoome);
                Grids_GridDouble g2;
                g2 = gf.create(
                        Directory,
                        g,
                        startRowIndex,
                        startColIndex,
                        endRowIndex,
                        endColIndex,
                        hoome);
                init(g2, false);

            }
            initChunks(gridFile);
        } else {
            // Assume ESRI AsciiFile
            ChunkNRows = chunkNRows;
            ChunkNCols = chunkNCols;
            NRows = endRowIndex - startRowIndex + 1L;
            NCols = endColIndex - startColIndex + 1L;
            initNoDataValue(noDataValue);
            Name = Directory.getName();
            initNChunkRows();
            initNChunkCols();
            ChunkIDChunkMap = new TreeMap<>();
            ChunkIDsofChunksWorthSwapping = new HashSet<>();
            Statistics = statistics;
            Statistics.Grid = this;
            String filename = gridFile.getName();
            double value;
            if (filename.endsWith("asc") || filename.endsWith("txt")) {
                Grids_ESRIAsciiGridImporter eagi;
                eagi = new Grids_ESRIAsciiGridImporter(
                        gridFile,
                        ge);
                Grids_ESRIAsciiGridHeader header = eagi.readHeaderObject();
                //long inputNcols = ( Long ) header[ 0 ];
                //long inputNrows = ( Long ) header[ 1 ];
                initDimensions(header, startRowIndex, startColIndex);
                double gridFileNoDataValue = header.NoDataValue.doubleValue();
                long row;
                long col;
                Grids_AbstractGridChunkDouble chunk;
                Grids_GridChunkDouble gridChunk;
                // Read Data into Chunks. This starts with the last row and ends with the first.
                if (gridFileNoDataValue == NoDataValue) {
                    if (statistics.getClass().getName().equalsIgnoreCase(Grids_GridDoubleStatistics.class.getName())) {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                initCell(row, col, value, false);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    } else {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                if (value == gridFileNoDataValue) {
                                    value = NoDataValue;
                                }
                                initCell(row, col, value, true);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    }
                } else {
                    if (statistics.getClass().getName().equalsIgnoreCase(Grids_GridDoubleStatistics.class.getName())) {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                if (value == gridFileNoDataValue) {
                                    value = NoDataValue;
                                }
                                initCell(row, col, value, false);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    } else {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                initCell(row, col, value, true);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    }
                }
            }
        }
    }

    private void init(
            Grids_GridDoubleStatistics statistics,
            File gridFile,
            boolean hoome) {
        ge.checkAndMaybeFreeMemory(hoome);
        Statistics = statistics;
        Statistics.init(this);
        // Set to report every 10%
        int reportN;
        Grids_Processor gp;
        gp = ge.getProcessor();
        if (gridFile.isDirectory()) {
            if (true) {
                Grids_GridDoubleFactory gf;
                gf = new Grids_GridDoubleFactory(
                        ge,
                        gp.GridDoubleFactory.Directory,
                        gp.GridChunkDoubleFactory,
                        gp.DefaultGridChunkDoubleFactory,
                        gp.GridIntFactory.NoDataValue,
                        gp.GridDoubleFactory.ChunkNRows,
                        gp.GridDoubleFactory.ChunkNCols,
                        null,
                        statistics);
                File thisFile = new File(
                        gridFile,
                        "thisFile");
                ObjectInputStream ois;
                ois = Generic_StaticIO.getObjectInputStream(thisFile);
                Grids_GridDouble g;
                g = (Grids_GridDouble) gf.create(
                        Directory,
                        thisFile,
                        ois,
                        hoome);
            }
            initChunks(gridFile);
        } else {
            // Assume ESRI AsciiFile
            Name = Directory.getName();
            ChunkIDChunkMap = new TreeMap<>();
            ChunkIDsofChunksWorthSwapping = new HashSet<>();
            Statistics = statistics;
            Statistics.init(this);
            String filename = gridFile.getName();
            double value;
            if (filename.endsWith("asc") || filename.endsWith("txt")) {
                Grids_ESRIAsciiGridImporter eagi;
                eagi = new Grids_ESRIAsciiGridImporter(
                        gridFile,
                        ge);
                Grids_ESRIAsciiGridHeader header = eagi.readHeaderObject();
                //long inputNcols = ( Long ) header[ 0 ];
                //long inputNrows = ( Long ) header[ 1 ];
                NCols = header.NCols;
                NRows = header.NRows;
                ChunkNRows = gp.GridDoubleFactory.ChunkNRows;
                ChunkNCols = gp.GridDoubleFactory.ChunkNCols;
                initNChunkRows();
                initNChunkCols();
                initDimensions(header, 0, 0);
                reportN = (int) (NRows - 1) / 10;
                double gridFileNoDataValue = header.NoDataValue.doubleValue();
                Grids_AbstractGridChunkDouble chunk;
                Grids_GridChunkDouble gridChunk;
                long row;
                long col;
                // Read Data into Chunks. This starts with the last row and ends with the first.
                if (gridFileNoDataValue == NoDataValue) {
                    if (statistics.getClass().getName().equalsIgnoreCase(Grids_GridDoubleStatistics.class.getName())) {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                initCell(row, col, value, false);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    } else {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                if (value == gridFileNoDataValue) {
                                    value = NoDataValue;
                                }
                                initCell(row, col, value, true);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    }
                } else {
                    if (statistics.getClass().getName().equalsIgnoreCase(Grids_GridDoubleStatistics.class.getName())) {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                if (value == gridFileNoDataValue) {
                                    value = NoDataValue;
                                }
                                initCell(row, col, value, false);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    } else {
                        for (row = (NRows - 1); row > -1; row--) {
                            ge.checkAndMaybeFreeMemory(hoome);
                            ge.initNotToSwap();
                            for (col = 0; col < NCols; col++) {
                                value = eagi.readDouble();
                                if (value == gridFileNoDataValue) {
                                    value = NoDataValue;
                                }
                                initCell(row, col, value, true);
                            }
                            if (row % reportN == 0) {
                                System.out.println("Done row " + row);
                            }
                            ge.checkAndMaybeFreeMemory(hoome);
                        }
                    }
                }
            }
        }
    }

    /**
     * Attempts to load into the memory cache the chunk with chunk ID chunkID.
     *
     * @param chunkID The chunk ID of the chunk to be restored.
     */
    @Override
    public void loadIntoCacheChunk(Grids_2D_ID_int chunkID) {
        boolean isInCache = isInCache(chunkID);
        if (!isInCache) {
            File f = new File(getDirectory(),
                    "" + chunkID.getRow() + "_" + chunkID.getCol());
            Object o = Generic_StaticIO.readObject(f);
            Grids_AbstractGridChunkDouble chunk = null;
            if (o.getClass() == Grids_GridChunkDoubleArray.class) {
                Grids_GridChunkDoubleArray c;
                c = (Grids_GridChunkDoubleArray) o;
                chunk = c;
            } else if (o.getClass() == Grids_GridChunkDoubleMap.class) {
                Grids_GridChunkDoubleMap c;
                c = (Grids_GridChunkDoubleMap) o;
                chunk = c;
            } else if (o.getClass() == Grids_GridChunkDouble.class) {
                Grids_GridChunkDouble c;
                c = (Grids_GridChunkDouble) o;
                chunk = c;
            } else {
                throw new Error("Unrecognised type of chunk or null "
                        + this.getClass().getName()
                        + ".loadIntoCacheChunk(ChunkID(" + chunkID.toString() + "))");
            }
            chunk.ge = ge;
            chunk.initGrid(this);
            chunk.initChunkID(chunkID);
            ChunkIDChunkMap.put(chunkID, chunk);
            if (!(chunk instanceof Grids_GridChunkDouble)) {
                ChunkIDsofChunksWorthSwapping.add(chunkID);
            }
            ge.setDataToSwap(true);
        }
    }

    /**
     *
     * @param row
     * @param col
     * @param value
     * @param fast
     */
    private void initCell(
            long row, long col, double value, boolean fast) {
        Grids_AbstractGridChunkDouble chunk;
        Grids_GridChunkDouble gridChunk;
        int chunkRow;
        int chunkCol;
        Grids_2D_ID_int chunkID;
        chunkRow = getChunkRow(row);
        chunkCol = getChunkCol(col);
        chunkID = new Grids_2D_ID_int(chunkRow, chunkCol);
        /**
         * Ensure this chunkID is not swapped and initialise it if it does not
         * already exist.
         */
        ge.addToNotToSwap(this, chunkID);
        if (!ChunkIDChunkMap.containsKey(chunkID)) {
            gridChunk = new Grids_GridChunkDouble(this, chunkID, value);
            ChunkIDChunkMap.put(chunkID, gridChunk);
            if (!(gridChunk instanceof Grids_GridChunkDouble)) {
                ChunkIDsofChunksWorthSwapping.add(chunkID);
            }
        } else {
            Grids_AbstractGridChunk c;
            c = ChunkIDChunkMap.get(chunkID);
            if (c == null) {
                loadIntoCacheChunk(chunkID, ge.HOOME);
            }
            chunk = (Grids_AbstractGridChunkDouble) ChunkIDChunkMap.get(chunkID);
            if (chunk instanceof Grids_GridChunkDouble) {
                gridChunk = (Grids_GridChunkDouble) chunk;
                if (value != gridChunk.Value) {
                    // Convert chunk to another type
                    chunk = ge.getProcessor().DefaultGridChunkDoubleFactory.create(
                            chunk,
                            chunkID);
                    ChunkIDChunkMap.put(chunkID, chunk);
                    if (!(chunk instanceof Grids_GridChunkDouble)) {
                        ChunkIDsofChunksWorthSwapping.add(chunkID);
                    }
                    chunk.initCell(getCellRow(row), getCellCol(col), value);
                }
            } else {
                if (fast) {
                    initCellFast(chunk, row, col, value);
                } else {
                    initCell(chunk,
                            chunkID, row, col, value);
                }
            }
        }
    }

    /**
     * @return Grids_AbstractGridChunkDouble for the given chunkID.
     * @param chunkID
     */
    @Override
    public Grids_AbstractGridChunkDouble getGridChunk(
            Grids_2D_ID_int chunkID) {
        if (isInGrid(chunkID)) {
            if (ChunkIDChunkMap.get(chunkID) == null) {
                loadIntoCacheChunk(chunkID);
            }
            return (Grids_AbstractGridChunkDouble) ChunkIDChunkMap.get(chunkID);
        }
        return null;
    }

    /**
     * @return Grids_AbstractGridChunkDouble for the given chunkID.
     * @param chunkID
     */
    @Override
    public Grids_AbstractGridChunkDouble getGridChunk(
            Grids_2D_ID_int chunkID,
            int chunkRow,
            int chunkCol) {
        if (isInGrid(chunkRow, chunkCol)) {
            if (ChunkIDChunkMap.get(chunkID) == null) {
                loadIntoCacheChunk(chunkID);
            }
            return (Grids_AbstractGridChunkDouble) ChunkIDChunkMap.get(chunkID);
        }
        return null;
    }

    /**
     * If newValue and oldValue are the same then statistics won't change. A
     * test might be appropriate in set cell so that this method is not called.
     *
     * WARNING! This should not be public, please don't use it. The reason it
     * has been made public is to allow access from chunk setCell methods which
     * may be accessed directly instead of via setCell in this class.
     *
     * @param newValue The value replacing oldValue.
     * @param oldValue The value being replaced.
     */
    protected void upDateGridStatistics(
            double newValue,
            double oldValue) {
        if (Statistics.getClass() == Grids_GridDoubleStatistics.class) {
            boolean hoome;
            hoome = ge.HOOME;
            if (newValue != NoDataValue) {
                if (oldValue != NoDataValue) {
                    BigDecimal oldValueBD = new BigDecimal(oldValue);
                    Statistics.setN(Statistics.getN(hoome) - 1);
                    Statistics.setSum(Statistics.getSum(hoome).subtract(oldValueBD));
                    double min = Statistics.getMin(false, hoome).doubleValue();
                    if (oldValue == min) {
                        Statistics.setNMin(Statistics.getNMin() - 1);
                    }
                    double max = Statistics.getMax(false, hoome).doubleValue();
                    if (oldValue == max) {
                        Statistics.setNMax(Statistics.getNMax() - 1);
                    }
                }
                BigDecimal newValueBD = new BigDecimal(newValue);
                Statistics.setN(Statistics.getN(hoome) + 1);
                Statistics.setSum(Statistics.getSum(hoome).add(newValueBD));
                updateStatistics(newValue);
                if (Statistics.getNMin() < 1) {
                    // The Statistics need recalculating
                    Statistics.update();
                }
                if (Statistics.getNMax() < 1) {
                    // The Statistics need recalculating
                    Statistics.update();
                }
            }
        } else {
            if (newValue != oldValue) {
                ((Grids_GridDoubleStatisticsNotUpdated) Statistics).setUpToDate(false);
            }
        }
    }

    /**
     * @return NoDataValue.
     *
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public final double getNoDataValue(
            boolean hoome) {
        try {
            double result = NoDataValue;
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                if (!ge.swapChunk(ge.HOOMEF)) {
                    throw e;
                }
                ge.initMemoryReserve(hoome);
                return getNoDataValue(
                        hoome);
            } else {
                throw e;
            }
        }
    }

    public final double getNoDataValue() {
        return NoDataValue;
    }

    /**
     * Initialises NoDataValue as noDataValue with the following exceptions. If
     * noDataValue is NaN or if noDataValue is Double.NEGATIVE_INFINITY or
     * Double.POSITIVE_INFINITY then NoDataValue is left as the default of
     * Integer.MIN_VALUE and a warning message is written to std.out.
     *
     * @param noDataValue The value NoDataValue is initialised to.
     */
    protected final void initNoDataValue(
            double noDataValue) {
        if (Double.isNaN(noDataValue)) {
            System.out.println("NoDataValue cannot be set to NaN! NoDataValue remains as " + NoDataValue);
        } else if (Double.isInfinite(noDataValue)) {
            System.out.println("NoDataValue cannot be infinite! NoDataValue remains as " + NoDataValue);
        } else {
            NoDataValue = noDataValue;
        }
    }

    /**
     *
     * For getting the value at row, col.
     *
     * @param row
     * @param col
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public double getCell(
            long row,
            long col,
            boolean hoome) {
        try {
            double result = getCell(row, col);
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(row), getChunkCol(col));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getCell(row, col, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * For getting the value at row, col.
     *
     * @param row
     * @param col
     * @return
     */
    public double getCell(
            long row,
            long col) {
//        boolean isInGrid = isInGrid(row, col);
//        if (isInGrid) {
        int chunkRow = getChunkRow(row);
        int chunkCol = getChunkCol(col);
        Grids_AbstractGridChunkDouble c;
        c = (Grids_AbstractGridChunkDouble) getChunk(chunkRow, chunkCol);
        int cellRow = getCellRow(row);
        int cellCol = getCellCol(col);
        return getCell(c, cellRow, cellCol);
//        }
//        return NoDataValue;
    }

    /**
     * For getting the value in chunk at cellRow, cellCol.
     *
     * @param chunk
     * @return Value at position given by chunk row index _ChunkRowIndex, chunk
     * column index _ChunkColIndex, chunk cell row index cellRow, chunk cell
     * column index cellCol.
     * @param cellRow The chunk cell row index of the cell thats value is
     * returned.
     * @param cellCol The chunk cell column index of the cell thats value is
     * returned.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public double getCell(
            Grids_AbstractGridChunkDouble chunk,
            int cellRow,
            int cellCol,
            boolean hoome) {
        try {
            double result = getCell(chunk, cellRow, cellCol);
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                freeSomeMemoryAndResetReserve(chunk.getChunkID(), e);
                return getCell(chunk, cellRow, cellCol, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * For getting the value in chunk at cellRow, cellCol.
     *
     * @param chunk
     * @param cellRow The chunk cell row index of the cell thats value is
     * returned.
     * @param cellCol The chunk cell column index of the cell thats value is
     * returned.
     * @return Value at position given by chunk row index _ChunkRowIndex, chunk
     * column index _ChunkColIndex, chunk cell row index cellRow, chunk cell
     * column index cellCol.
     *
     */
    public double getCell(
            Grids_AbstractGridChunkDouble chunk,
            int cellRow,
            int cellCol) {
        if (chunk.getClass() == Grids_GridChunkDoubleArray.class) {
            return ((Grids_GridChunkDoubleArray) chunk).getCell(cellRow, cellCol);
        } else if (chunk.getClass() == Grids_GridChunkDoubleMap.class) {
            return ((Grids_GridChunkDoubleMap) chunk).getCell(cellRow, cellCol);
        } else {
            return ((Grids_GridChunkDouble) chunk).getCell(cellRow, cellCol);
        }
    }

    /**
     * For getting the value at x-coordinate x, y-coordinate y.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public final double getCell(
            double x,
            double y,
            boolean hoome) {
        try {
            double result = getCell(x, y);
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(y), getChunkCol(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getCell(x, y, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * For getting the value at x-coordinate x, y-coordinate y.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @return
     */
    public final double getCell(
            double x,
            double y) {
        long row = getRow(y);
        long col = getCol(x);
        boolean isInGrid = isInGrid(row, col);
        if (isInGrid) {
            return getCell(row, col);
        }
        return NoDataValue;
    }

    /**
     * For getting the value of at cellID.
     *
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public final double getCell(
            Grids_2D_ID_long cellID,
            boolean hoome) {
        try {
            double result = getCell(cellID.getRow(), cellID.getCol());
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(cellID.getRow()),
                        getChunkCol(cellID.getCol()));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getCell(cellID, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * For setting the value at x-coordinate x, y-coordinate y.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @param newValue .
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public final void setCell(
            double x,
            double y,
            double newValue,
            boolean hoome) {
        try {
            setCell(x, y, newValue);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(y), getChunkCol(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                setCell(x, y, newValue, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * For setting the value at x-coordinate x, y-coordinate y.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @param value
     */
    public final void setCell(
            double x,
            double y,
            double value) {
        setCell(getRow(x), getCol(y), value);
    }

    /**
     * For returning the value of the cell with cell Grids_2D_ID_int _CellID and
     * setting it to newValue.
     *
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param value .
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public final void setCell(
            Grids_2D_ID_long cellID,
            double value,
            boolean hoome) {
        try {
            setCell(cellID.getRow(), cellID.getCol(), value);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(cellID.getRow()),
                        getChunkCol(cellID.getCol()));
                freeSomeMemoryAndResetReserve(chunkID, e);
                setCell(cellID, value, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param value
     * @param row The cell row index.
     * @param col The cell column index.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public final void setCell(long row, long col, double value,
            boolean hoome) {
        try {
            setCell(row, col, value);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(row), getChunkCol(col));
                freeSomeMemoryAndResetReserve(chunkID, e);
                setCell(row, col, value, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * For setting the value at row, col.
     *
     * @param row
     * @param col
     * @param value
     */
    public void setCell(
            long row,
            long col,
            double value) {
        int chunkRow = getChunkRow(row);
        int chunkCol = getChunkCol(col);
        int cellRow = getCellRow(row);
        int cellCol = getCellCol(col);
        Grids_AbstractGridChunkDouble chunk;
        chunk = (Grids_AbstractGridChunkDouble) getGridChunk(chunkRow,
                chunkCol);
        setCell(chunk, cellRow, cellCol, value);
    }

    /**
     * For setting the value at chunkRow, chunkCol, cellRow, cellCol.
     *
     * @param chunkRow
     * @param chunkCol
     * @param cellRow
     * @param cellCol
     * @param value
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public void setCell(
            int chunkRow,
            int chunkCol,
            int cellRow,
            int cellCol,
            double value,
            boolean hoome) {
        try {
            setCell(chunkRow, chunkCol, cellRow, cellCol, value);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID;
                chunkID = new Grids_2D_ID_int(chunkRow, chunkCol);
                freeSomeMemoryAndResetReserve(chunkID, e);
                setCell(chunkRow, chunkCol, cellRow, cellCol, value, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * For setting the value at chunkRow, chunkCol, cellRow, cellCol.
     *
     * @param chunkRow
     * @param chunkCol
     * @param cellRow
     * @param cellCol
     * @param value
     */
    public void setCell(
            int chunkRow,
            int chunkCol,
            int cellRow,
            int cellCol,
            double value) {
        Grids_AbstractGridChunkDouble chunk;
        chunk = (Grids_AbstractGridChunkDouble) getGridChunk(chunkRow,
                chunkCol);
        setCell(chunk, cellRow, cellCol, value);
    }

    /**
     * For setting the value in chunk at cellRow, cellCol.
     *
     * @param chunk
     * @param cellRow
     * @param cellCol
     * @param newValue
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public void setCell(
            Grids_AbstractGridChunkDouble chunk,
            int cellRow,
            int cellCol,
            double newValue,
            boolean hoome) {
        try {
            setCell(chunk, cellRow, cellCol, newValue);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                freeSomeMemoryAndResetReserve(chunk.getChunkID(), e);
                setCell(chunk, cellRow, cellCol, newValue, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param chunk
     * @param cellCol
     * @param cellRow
     * @param value
     */
    public void setCell(
            Grids_AbstractGridChunkDouble chunk,
            int cellRow,
            int cellCol,
            double value) {
        double v;
        if (chunk instanceof Grids_GridChunkDoubleArray) {
            v = ((Grids_GridChunkDoubleArray) chunk).setCell(cellRow, cellCol,
                    value);
        } else if (chunk instanceof Grids_GridChunkDoubleMap) {
            v = ((Grids_GridChunkDoubleMap) chunk).setCell(cellRow, cellCol,
                    value);
        } else {
            Grids_GridChunkDouble c;
            c = (Grids_GridChunkDouble) chunk;
            if (value != c.Value) {
                // Convert chunk to another type
                Grids_2D_ID_int chunkID;
                chunkID = chunk.getChunkID();
                chunk = convertToAnotherTypeOfChunk(chunk, chunkID);
                v = chunk.setCell(cellRow, cellCol, value);
            } else {
                v = c.Value;
            }
        }
        // Update Statistics
        upDateGridStatistics(value, v);
    }

    /**
     * Convert chunk to another type of chunk.
     */
    private Grids_AbstractGridChunkDouble convertToAnotherTypeOfChunk(
            Grids_AbstractGridChunkDouble chunk,
            Grids_2D_ID_int chunkID) {
        Grids_AbstractGridChunkDouble result;
        Grids_AbstractGridChunkDoubleFactory f;
        f = ge.getProcessor().DefaultGridChunkDoubleFactory;
        result = f.create(chunk, chunkID);
        ChunkIDChunkMap.put(chunkID, result);
        if (!(chunk instanceof Grids_GridChunkDouble)) {
            ChunkIDsofChunksWorthSwapping.add(chunkID);
        }
        return result;
    }

    /**
     * Initialises the value in chunk at row, col.
     *
     * @param chunk
     * @param chunkID
     * @param row
     * @param col
     * @param value
     */
    protected void initCell(
            Grids_AbstractGridChunkDouble chunk,
            Grids_2D_ID_int chunkID,
            long row, long col, double value) {
        if (chunk instanceof Grids_GridChunkDouble) {
            Grids_GridChunkDouble gridChunk = (Grids_GridChunkDouble) chunk;
            if (value != gridChunk.Value) {
                chunk = convertToAnotherTypeOfChunk(chunk, chunkID);
                chunk.initCell(getCellRow(row), getCellCol(col), value);
            } else {
                return;
            }
        }
        chunk.initCell(getCellRow(row), getCellCol(col), value);
        // Update Statistics
        if (value != NoDataValue) {
            if (!(Statistics instanceof Grids_GridDoubleStatisticsNotUpdated)) {
                updateStatistics(value);
            }
        }
    }

    protected void updateStatistics(double value) {
        if (!Double.isNaN(value) && Double.isFinite(value)) {
            boolean h = ge.HOOME;
            BigDecimal valueBD = new BigDecimal(value);
            Statistics.setN(Statistics.getN(h) + 1);
            Statistics.setSum(Statistics.getSum(h).add(valueBD));
            double min = Statistics.getMin(false, h).doubleValue();
            if (value < min) {
                Statistics.setNMin(1);
                Statistics.setMin(value);
            } else {
                if (value == min) {
                    Statistics.setNMin(Statistics.getNMin() + 1);
                }
            }
            double max = Statistics.getMax(false, h).doubleValue();
            if (value > max) {
                Statistics.setNMax(1);
                Statistics.setMax(value);
            } else {
                if (value == max) {
                    Statistics.setNMax(Statistics.getNMax() + 1);
                }
            }
        }
    }

    /**
     * Initialises the value at _CellRowIndex, _CellColIndex and does nothing
     * about Statistics
     *
     * @param chunk
     * @param row
     * @param col
     * @param value
     */
    protected void initCellFast(
            Grids_AbstractGridChunkDouble chunk,
            long row,
            long col,
            double value) {
//        int chunkRow = getChunkRow(row);
//        int chunkCol = getChunkCol(col);
//        Grids_2D_ID_int chunkID = new Grids_2D_ID_int(chunkRow, chunkCol);
//        Grids_AbstractGridChunkDouble chunk = getGridChunk(chunkID);
        chunk.initCell(getCellRow(row), getCellCol(col), value);
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at x-coordinate x, y-coordinate y, and
     * radius distance.
     * @param x the x-coordinate of the circle centre from which cell values are
     * returned.
     * @param y the y-coordinate of the circle centre from which cell values are
     * returned.
     * @param distance the radius of the circle for which intersected cell
     * values are returned.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown. TODO
     */
    public double[] getCells(
            double x,
            double y,
            double distance,
            boolean hoome) {
        try {
            double[] result = getCells(
                    x,
                    y,
                    distance);
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                long row = getRow(y);
                long col = getCol(x);
                HashSet<Grids_2D_ID_int> chunkIDs = getChunkIDs(
                        distance,
                        x,
                        y,
                        row,
                        col);
                freeSomeMemoryAndResetReserve(chunkIDs, e);
                return getCells(
                        x,
                        y,
                        distance,
                        hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at x-coordinate x, y-coordinate y, and
     * radius distance.
     * @param x the x-coordinate of the circle centre from which cell values are
     * returned.
     * @param y the y-coordinate of the circle centre from which cell values are
     * returned.
     * @param distance the radius of the circle for which intersected cell
     * values are returned.
     */
    protected double[] getCells(
            double x,
            double y,
            double distance) {
        return getCells(
                x,
                y,
                getRow(y),
                getCol(x),
                distance);
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at centroid of cell given by cell row
     * index row, cell column index col, and radius distance.
     * @param row the row index for the cell that'Statistics centroid is the
     * circle centre from which cell values are returned.
     * @param col the column index for the cell that'Statistics centroid is the
     * circle centre from which cell values are returned.
     * @param distance the radius of the circle for which intersected cell
     * values are returned.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public double[] getCells(
            long row,
            long col,
            double distance,
            boolean hoome) {
        try {
            double[] result = getCells(
                    row,
                    col,
                    distance);
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                double x = getCellXDouble(col);
                double y = getCellYDouble(row);
                HashSet chunkIDs = getChunkIDs(
                        distance,
                        x,
                        y,
                        row,
                        col);
                freeSomeMemoryAndResetReserve(chunkIDs, e);
                return getCells(
                        row,
                        col,
                        distance,
                        hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at centroid of cell given by cell row
     * index row, cell column index col, and radius distance.
     * @param row the row index for the cell that'Statistics centroid is the
     * circle centre from which cell values are returned.
     * @param col the column index for the cell that'Statistics centroid is the
     * circle centre from which cell values are returned.
     * @param distance the radius of the circle for which intersected cell
     * values are returned.
     */
    protected double[] getCells(
            long row,
            long col,
            double distance) {
        return getCells(
                getCellXDouble(col),
                getCellYDouble(row),
                row,
                col,
                distance);
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at x-coordinate x, y-coordinate y, and
     * radius distance.
     * @param x The x-coordinate of the circle centre from which cell values are
     * returned.
     * @param y The y-coordinate of the circle centre from which cell values are
     * returned.
     * @param row The row index at y.
     * @param col The column index at x.
     * @param distance The radius of the circle for which intersected cell
     * values are returned.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown. TODO
     */
    public double[] getCells(
            double x,
            double y,
            long row,
            long col,
            double distance,
            boolean hoome) {
        try {
            double[] result = getCells(
                    x,
                    y,
                    row,
                    col,
                    distance);
            ge.checkAndMaybeFreeMemory(hoome);
            return result;
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                HashSet chunkIDs = getChunkIDs(
                        distance,
                        x,
                        y,
                        row,
                        col);
                freeSomeMemoryAndResetReserve(chunkIDs, e);
                return getCells(
                        x,
                        y,
                        row,
                        col,
                        distance,
                        hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at x-coordinate x, y-coordinate y, and
     * radius distance.
     * @param x The x-coordinate of the circle centre from which cell values are
     * returned.
     * @param y The y-coordinate of the circle centre from which cell values are
     * returned.
     * @param row The row index at y.
     * @param col The column index at x.
     * @param distance The radius of the circle for which intersected cell
     * values are returned.
     */
    protected double[] getCells(
            double x,
            double y,
            long row,
            long col,
            double distance) {
        double[] cells;
        int cellDistance = (int) Math.ceil(distance / getCellsizeDouble(true));
        cells = new double[((2 * cellDistance) + 1) * ((2 * cellDistance) + 1)];
        long p;
        long q;
        double thisX;
        double thisY;
        int count = 0;
        for (p = row - cellDistance; p <= row + cellDistance; p++) {
            thisY = getCellYDouble(row);
            for (q = col - cellDistance; q <= col + cellDistance; q++) {
                thisX = getCellXDouble(col);
                if (Grids_Utilities.distance(x, y, thisX, thisY) <= distance) {
                    cells[count] = getCell(
                            p,
                            q);
                    count++;
                }
            }
        }
        // Trim cells
        System.arraycopy(
                cells,
                0,
                cells,
                0,
                count);
        return cells;
    }

    /**
     * @return the average of the nearest data values to point given by
     * x-coordinate x, y-coordinate y as a double.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    @Override
    protected double getNearestValueDouble(
            double x,
            double y) {
        double result = getCell(x, y);
        if (result == NoDataValue) {
            result = getNearestValueDouble(x, y, getRow(y), getCol(x));
        }
        return result;
    }

    /**
     * @param row The row index from which average of the nearest data values is
     * returned.
     * @param col The column index from which average of the nearest data values
     * is returned.
     * @return the average of the nearest data values to position given by row
     * index rowIndex, column index colIndex
     */
    @Override
    protected double getNearestValueDouble(
            long row,
            long col) {
        double result = getCell(
                row,
                col);
        if (result == NoDataValue) {
            result = getNearestValueDouble(
                    getCellXDouble(col),
                    getCellYDouble(row),
                    row,
                    col);
        }
        return result;
    }

    /**
     * @return the average of the nearest data values to point given by
     * x-coordinate x, y-coordinate y in position given by row index rowIndex,
     * column index colIndex
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param row the row index from which average of the nearest data values is
     * returned
     * @param col the column index from which average of the nearest data values
     * is returned
     */
    @Override
    protected double getNearestValueDouble(
            double x,
            double y,
            long row,
            long col) {
        Grids_2D_ID_long nearestCellID = getNearestCellID(
                x,
                y,
                row,
                col);
        double nearestValue = getCell(
                row,
                col);
        if (nearestValue == NoDataValue) {
            // Find a value Seeking outwards from nearestCellID
            // Initialise visitedSet1
            HashSet visitedSet = new HashSet();
            HashSet visitedSet1 = new HashSet();
            visitedSet.add(nearestCellID);
            visitedSet1.add(nearestCellID);
            // Initialise toVisitSet1
            HashSet toVisitSet1 = new HashSet();
            long p;
            long q;
            Grids_2D_ID_long cellID0;
            boolean isInGrid;
            for (p = -1; p < 2; p++) {
                for (q = -1; q < 2; q++) {
                    if (!(p == 0 && q == 0)) {
                        isInGrid = isInGrid(
                                row + p,
                                col + q);
                        if (isInGrid) {
                            cellID0 = new Grids_2D_ID_long(
                                    row + p,
                                    col + q);
                            toVisitSet1.add(cellID0);
                        }
                    }
                }
            }
            // Seek
            boolean foundValue = false;
            double value;
            HashSet values = new HashSet();
            HashSet visitedSet2;
            HashSet toVisitSet2;
            Iterator iterator;
            Grids_2D_ID_long cellID1;
            while (!foundValue) {
                visitedSet2 = new HashSet();
                toVisitSet2 = new HashSet();
                iterator = toVisitSet1.iterator();
                while (iterator.hasNext()) {
                    cellID0 = (Grids_2D_ID_long) iterator.next();
                    visitedSet2.add(cellID0);
                    value = getCell(cellID0, ge.HOOMET);
                    if (value != NoDataValue) {
                        foundValue = true;
                        values.add(cellID0);
                    } else {
                        // Add neighbours to toVisitSet2
                        for (p = -1; p < 2; p++) {
                            for (q = -1; q < 2; q++) {
                                if (!(p == 0 && q == 0)) {
                                    isInGrid = isInGrid(
                                            cellID0.getRow() + p,
                                            cellID0.getCol() + q);
                                    if (isInGrid) {
                                        cellID1 = new Grids_2D_ID_long(
                                                cellID0.getRow() + p,
                                                cellID0.getCol() + q);
                                        toVisitSet2.add(cellID1);
                                    }
                                }
                            }
                        }
                    }
                }
                toVisitSet2.removeAll(visitedSet1);
                toVisitSet2.removeAll(visitedSet2);
                visitedSet.addAll(visitedSet2);
                visitedSet1 = visitedSet2;
                toVisitSet1 = toVisitSet2;
            }
            double distance;
            double minDistance = Integer.MAX_VALUE;
            // Go through values and find the closest
            HashSet closest = new HashSet();
            iterator = values.iterator();
            while (iterator.hasNext()) {
                cellID0 = (Grids_2D_ID_long) iterator.next();
                distance = Grids_Utilities.distance(
                        x,
                        y,
                        getCellXDouble(cellID0),
                        getCellYDouble(cellID0));
                if (distance < minDistance) {
                    closest.clear();
                    closest.add(cellID0);
                } else {
                    if (distance == minDistance) {
                        closest.add(cellID0);
                    }
                }
                minDistance = Math.min(
                        minDistance,
                        distance);
            }
            // Get cellIDs that are within distance of discovered value
            Grids_2D_ID_long[] cellIDs = getCellIDs(
                    x,
                    y,
                    minDistance);
            for (Grids_2D_ID_long cellID : cellIDs) {
                if (!visitedSet.contains(cellID)) {
                    if (getCell(cellID, ge.HOOMET) != NoDataValue) {
                        distance = Grids_Utilities.distance(x, y, getCellXDouble(cellID), getCellYDouble(cellID));
                        if (distance < minDistance) {
                            closest.clear();
                            closest.add(cellID);
                        } else {
                            if (distance == minDistance) {
                                closest.add(cellID);
                            }
                        }
                        minDistance = Math.min(
                                minDistance,
                                distance);
                    }
                }
            }
            // Go through the closest and calculate the average.
            value = 0;
            iterator = closest.iterator();
            while (iterator.hasNext()) {
                cellID0 = (Grids_2D_ID_long) iterator.next();
                value += getCell(cellID0, ge.HOOMET);
            }
            nearestValue = value / (double) closest.size();
        }
        return nearestValue;
    }

    /**
     * @return a Grids_2D_ID_long[] The CellIDs of the nearest cells with data
     * values to point given by x-coordinate x, y-coordinate y.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    @Override
    protected Grids_2D_ID_long[] getNearestValuesCellIDs(double x, double y) {
        double value = getCell(x, y);
        if (value == NoDataValue) {
            return getNearestValuesCellIDs(x, y, getRow(y), getCol(x));
        }
        Grids_2D_ID_long[] cellIDs = new Grids_2D_ID_long[1];
        cellIDs[0] = getCellID(x, y);
        return cellIDs;
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data
     * values to position given by row index rowIndex, column index colIndex.
     * @param row The row index from which the cell IDs of the nearest cells
     * with data values are returned.
     * @param col
     */
    @Override
    protected Grids_2D_ID_long[] getNearestValuesCellIDs(
            long row,
            long col) {
        double value = getCell(
                row,
                col);
        if (value == NoDataValue) {
            return getNearestValuesCellIDs(
                    getCellXDouble(col),
                    getCellYDouble(row),
                    row,
                    col);
        }
        Grids_2D_ID_long[] cellIDs = new Grids_2D_ID_long[1];
        cellIDs[0] = getCellID(
                row,
                col);
        return cellIDs;
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data
     * values nearest to point with position given by: x-coordinate x,
     * y-coordinate y; and, cell row index _CellRowIndex, cell column index
     * _CellColIndex.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param row The row index from which the cell IDs of the nearest cells
     * with data values are returned.
     * @param col The column index from which the cell IDs of the nearest cells
     * with data values are returned.
     */
    @Override
    protected Grids_2D_ID_long[] getNearestValuesCellIDs(
            double x,
            double y,
            long row,
            long col) {
        Grids_2D_ID_long[] nearestCellIDs = new Grids_2D_ID_long[1];
        nearestCellIDs[0] = getNearestCellID(
                x,
                y,
                row,
                col);
        double nearestCellValue = getCell(
                row,
                col);
        if (nearestCellValue == NoDataValue) {
            // Find a value Seeking outwards from nearestCellID
            // Initialise visitedSet1
            HashSet visitedSet = new HashSet();
            HashSet visitedSet1 = new HashSet();
            visitedSet.add(nearestCellIDs[0]);
            visitedSet1.add(nearestCellIDs[0]);
            // Initialise toVisitSet1
            HashSet toVisitSet1 = new HashSet();
            long p;
            long q;
            boolean isInGrid;
            Grids_2D_ID_long cellID;
            for (p = -1; p < 2; p++) {
                for (q = -1; q < 2; q++) {
                    if (!(p == 0 && q == 0)) {
                        isInGrid = isInGrid(
                                row + p,
                                col + q);
                        if (isInGrid) {
                            cellID = getCellID(
                                    row + p,
                                    col + q);
                            toVisitSet1.add(cellID);
                        }
                    }
                }
            }
            // Seek
            boolean foundValue = false;
            double value;
            HashSet values = new HashSet();
            HashSet visitedSet2;
            HashSet toVisitSet2;
            Iterator iterator;
            while (!foundValue) {
                visitedSet2 = new HashSet();
                toVisitSet2 = new HashSet();
                iterator = toVisitSet1.iterator();
                while (iterator.hasNext()) {
                    cellID = (Grids_2D_ID_long) iterator.next();
                    visitedSet2.add(cellID);
                    value = getCell(cellID,
                            ge.HOOMET);
                    if (value != NoDataValue) {
                        foundValue = true;
                        values.add(cellID);
                    } else {
                        // Add neighbours to toVisitSet2
                        for (p = -1; p < 2; p++) {
                            for (q = -1; q < 2; q++) {
                                if (!(p == 0 && q == 0)) {
                                    isInGrid = isInGrid(
                                            cellID.getRow() + p,
                                            cellID.getCol() + q);
                                    if (isInGrid) {
                                        cellID = getCellID(
                                                cellID.getRow() + p,
                                                cellID.getCol() + q);
                                        toVisitSet2.add(cellID);
                                    }
                                }
                            }
                        }
                    }
                }
                toVisitSet2.removeAll(visitedSet1);
                toVisitSet2.removeAll(visitedSet2);
                visitedSet.addAll(visitedSet2);
                visitedSet1 = visitedSet2;
                toVisitSet1 = toVisitSet2;
            }
            double distance;
            double minDistance = Double.MAX_VALUE;
            // Go through values and find the closest
            HashSet closest = new HashSet();
            iterator = values.iterator();
            while (iterator.hasNext()) {
                cellID = (Grids_2D_ID_long) iterator.next();
                distance = Grids_Utilities.distance(
                        x,
                        y,
                        getCellXDouble(cellID),
                        getCellYDouble(cellID));
                if (distance < minDistance) {
                    closest.clear();
                    closest.add(cellID);
                } else {
                    if (distance == minDistance) {
                        closest.add(cellID);
                    }
                }
                minDistance = Math.min(
                        minDistance,
                        distance);
            }
            // Get cellIDs that are within distance of discovered value
            Grids_2D_ID_long[] cellIDs = getCellIDs(
                    x,
                    y,
                    minDistance);
            for (Grids_2D_ID_long cellID1 : cellIDs) {
                if (!visitedSet.contains(cellID1)) {
                    if (getCell(cellID1, ge.HOOMET) != NoDataValue) {
                        distance = Grids_Utilities.distance(x, y, getCellXDouble(cellID1), getCellYDouble(cellID1));
                        if (distance < minDistance) {
                            closest.clear();
                            closest.add(cellID1);
                        } else {
                            if (distance == minDistance) {
                                closest.add(cellID1);
                            }
                        }
                        minDistance = Math.min(
                                minDistance,
                                distance);
                    }
                }
            }
            // Go through the closest and put into an array
            nearestCellIDs = new Grids_2D_ID_long[closest.size()];
            iterator = closest.iterator();
            int counter = 0;
            while (iterator.hasNext()) {
                nearestCellIDs[counter] = (Grids_2D_ID_long) iterator.next();
                counter++;
            }
        }
        return nearestCellIDs;
    }

    /**
     * @return the distance to the nearest data value from point given by
     * x-coordinate x, y-coordinate y as a double.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    @Override
    protected double getNearestValueDoubleDistance(
            double x,
            double y) {
        double result = getCell(x, y);
        if (result == NoDataValue) {
            result = getNearestValueDoubleDistance(x, y, getRow(y), getCol(x));
        }
        return result;
    }

    /**
     * @return the distance to the nearest data value from position given by row
     * index rowIndex, column index colIndex as a double.
     * @param row The cell row index of the cell from which the distance nearest
     * to the nearest cell value is returned.
     * @param col The cell column index of the cell from which the distance
     * nearest to the nearest cell value is returned.
     */
    protected double getNearestValueDoubleDistance(
            long row,
            long col) {
        double result = getCell(
                row,
                col);
        if (result == NoDataValue) {
            result = getNearestValueDoubleDistance(getCellXDouble(col),
                    getCellYDouble(row),
                    row,
                    col);
        }
        return result;
    }

    /**
     * @return the distance to the nearest data value from: point given by
     * x-coordinate x, y-coordinate y in position given by row index rowIndex,
     * column index colIndex as a double.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param row The cell row index of the cell from which the distance nearest
     * to the nearest cell value is returned.
     * @param col The cell column index of the cell from which the distance
     * nearest to the nearest cell value is returned.
     */
    @Override
    protected double getNearestValueDoubleDistance(
            double x,
            double y,
            long row,
            long col) {
        double result = getCell(
                row,
                col);
        if (result == NoDataValue) {
            // Initialisation
            long long0;
            long long1;
            long longMinus1 = -1;
            long longTwo = 2;
            long longZero = 0;
            boolean boolean0;
            boolean boolean1;
            boolean boolean2;
            double double0;
            double double1;
            Grids_2D_ID_long nearestCellID = getNearestCellID(
                    x,
                    y,
                    row,
                    col);
            HashSet visitedSet = new HashSet();
            HashSet visitedSet1 = new HashSet();
            visitedSet.add(nearestCellID);
            visitedSet1.add(nearestCellID);
            HashSet toVisitSet1 = new HashSet();
            long p;
            long q;
            boolean isInGrid;
            Grids_2D_ID_long cellID;
            boolean foundValue = false;
            double value;
            HashSet values = new HashSet();
            HashSet visitedSet2;
            HashSet toVisitSet2;
            Iterator iterator;
            double distance;
            double minDistance = Double.MAX_VALUE;
            HashSet closest = new HashSet();
            // Find a value Seeking outwards from nearestCellID
            // Initialise toVisitSet1
            for (p = longMinus1; p < longTwo; p++) {
                for (q = longMinus1; q < longTwo; q++) {
                    boolean0 = (p == longZero);
                    boolean1 = (q == longZero);
                    boolean2 = !(boolean0 && boolean1);
                    if (boolean2) {
                        long0 = row + p;
                        long1 = col + q;
                        isInGrid = isInGrid(long0,
                                long1,
                                ge.HOOMET);
                        if (isInGrid) {
                            cellID = getCellID(long0,
                                    long1,
                                    ge.HOOMET);
                            toVisitSet1.add(cellID);
                        }
                    }
                }
            }
            // Seek
            while (!foundValue) {
                visitedSet2 = new HashSet();
                toVisitSet2 = new HashSet();
                iterator = toVisitSet1.iterator();
                while (iterator.hasNext()) {
                    cellID = (Grids_2D_ID_long) iterator.next();
                    visitedSet2.add(cellID);
                    value = getCell(cellID,
                            ge.HOOMET);
                    if (value != NoDataValue) {
                        foundValue = true;
                        values.add(cellID);
                    } else {
                        // Add neighbours to toVisitSet2
                        for (p = longMinus1; p < longTwo; p++) {
                            for (q = longMinus1; q < longTwo; q++) {
                                boolean0 = (p == longZero);
                                boolean1 = (q == longZero);
                                boolean2 = !(boolean0 && boolean1);
                                if (boolean2) {
                                    long0 = cellID.getRow() + p;
                                    long1 = cellID.getCol() + q;
                                    isInGrid = isInGrid(long0,
                                            long1,
                                            ge.HOOMET);
                                    if (isInGrid) {
                                        cellID = getCellID(long0,
                                                long1,
                                                ge.HOOMET);
                                        toVisitSet2.add(cellID);
                                    }
                                }
                            }
                        }
                    }
                }
                toVisitSet2.removeAll(visitedSet1);
                toVisitSet2.removeAll(visitedSet2);
                visitedSet.addAll(visitedSet2);
                visitedSet1 = visitedSet2;
                toVisitSet1 = toVisitSet2;
            }
            // Go through values and find the closest
            iterator = values.iterator();
            while (iterator.hasNext()) {
                cellID = (Grids_2D_ID_long) iterator.next();
                double0 = getCellXDouble(cellID,
                        ge.HOOMET);
                double1 = getCellYDouble(cellID,
                        ge.HOOMET);
                distance = Grids_Utilities.distance(
                        x,
                        y,
                        double0,
                        double1);
                if (distance < minDistance) {
                    closest.clear();
                    closest.add(cellID);
                } else {
                    if (distance == minDistance) {
                        closest.add(cellID);
                    }
                }
                minDistance = Math.min(
                        minDistance,
                        distance);
            }
            // Get cellIDs that are within distance of discovered value
            Grids_2D_ID_long[] cellIDs = getCellIDs(
                    x,
                    y,
                    minDistance);
            for (Grids_2D_ID_long cellID1 : cellIDs) {
                if (!visitedSet.contains(cellID1)) {
                    if (getCell(cellID1, ge.HOOMET) != NoDataValue) {
                        distance = Grids_Utilities.distance(x, y, getCellXDouble(cellID1), getCellYDouble(cellID1));
                        minDistance = Math.min(
                                minDistance,
                                distance);
                    }
                }
            }
            result = minDistance;
        } else {
            result = 0.0d;
        }
        return result;
    }

    /**
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param valueToAdd the value to be added to the cell containing the point
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public void addToCell(
            double x,
            double y,
            double valueToAdd,
            boolean hoome) {
        try {
            addToCell(x, y, valueToAdd);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(y), getChunkCol(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                addToCell(x, y, valueToAdd, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param valueToAdd the value to be added to the cell containing the point
     */
    public void addToCell(
            double x,
            double y,
            double valueToAdd) {
        addToCell(getRow(y), getCol(x), valueToAdd);
    }

    /**
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param valueToAdd the value to be added to the cell containing the point
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown.
     */
    public void addToCell(
            Grids_2D_ID_long cellID,
            double valueToAdd,
            boolean hoome) {
        try {
            addToCell(cellID, valueToAdd);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(cellID.getRow()),
                        getChunkCol(cellID.getCol()));
                freeSomeMemoryAndResetReserve(chunkID, e);
                addToCell(cellID, valueToAdd, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param valueToAdd the value to be added to the cell containing the point
     */
    public void addToCell(
            Grids_2D_ID_long cellID,
            double valueToAdd) {
        addToCell(cellID.getRow(), cellID.getCol(), valueToAdd);
    }

    /**
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     * @param valueToAdd the value to be added to the cell.
     * @param hoome If true then OutOfMemoryErrors are caught, swap operations
     * are initiated, then the method is re-called. If false then
     * OutOfMemoryErrors are caught and thrown. NB1. If cell is not contained in
     * this then then returns NoDataValue. NB2. Adding to NoDataValue is done as
     * if adding to a cell with value of 0. TODO: Check Arithmetic
     */
    public void addToCell(
            long row,
            long col,
            double valueToAdd,
            boolean hoome) {
        try {
            addToCell(row, col, valueToAdd);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRow(row), getChunkCol(col));
                freeSomeMemoryAndResetReserve(chunkID, e);
                addToCell(row, col, valueToAdd, hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param row the row index of the cell.
     * @param col the column index of the cell.
     * @param valueToAdd the value to be added to the cell. If the value is
     * NoDataValue the adding is done as if adding to a cell with value of 0.
     */
    public void addToCell(
            long row,
            long col,
            double valueToAdd) {
        boolean isInGrid = isInGrid(row, col);
        if (isInGrid) {
            double currentValue = getCell(row, col);
            if (currentValue != NoDataValue) {
                if (valueToAdd != NoDataValue) {
                    setCell(row, col, currentValue + valueToAdd);
                }
            } else {
                if (valueToAdd != NoDataValue) {
                    setCell(row, col, valueToAdd);
                }
            }
        }
    }

    /**
     *
     * @param value
     * @param hoome
     */
    public void initCells(
            double value,
            boolean hoome) {
        try {
            initCells(value);
            ge.checkAndMaybeFreeMemory(hoome);
        } catch (OutOfMemoryError e) {
            if (hoome) {
                ge.clearMemoryReserve();
                if (!ge.swapChunk(ge.HOOMEF)) {
                    throw e;
                }
                ge.initMemoryReserve(hoome);
                initCells(
                        value,
                        hoome);
            } else {
                throw e;
            }
        }
    }

    /**
     *
     * @param value
     */
    protected void initCells(double value) {
        Iterator<Grids_2D_ID_int> ite = ChunkIDChunkMap.keySet().iterator();
        int nChunks = ChunkIDChunkMap.size();
        Grids_AbstractGridChunkDouble chunk;
        int chunkNRows;
        int chunkNCols;
        int row;
        int col;
        Grids_2D_ID_int chunkID;
        int counter = 0;
        boolean hoome = true;
        while (ite.hasNext()) {
            ge.checkAndMaybeFreeMemory(hoome);
            System.out.println(
                    "Initialising Chunk " + counter
                    + " out of " + nChunks);
            counter++;
            chunkID = ite.next();
            chunk = (Grids_AbstractGridChunkDouble) ChunkIDChunkMap.get(chunkID);
            chunkNRows = getChunkNRows(chunkID, hoome);
            chunkNCols = getChunkNCols(chunkID, hoome);
            for (row = 0; row <= chunkNRows; row++) {
                for (col = 0; col <= chunkNCols; col++) {
                    chunk.setCell(
                            row,
                            col,
                            value,
                            hoome);
                }
            }
        }
    }

    /**
     * @return A Grids_GridDoubleIterator for iterating over the cell values in
     * this.
     */
    @Override
    public Grids_GridDoubleIterator iterator() {
        return new Grids_GridDoubleIterator(this);
    }

    @Override
    public Grids_GridDoubleStatistics getStatistics() {
        return Statistics;
    }

    public void initStatistics(Grids_GridDoubleStatistics statistics) {
        Statistics = statistics;
    }

    @Override
    public double getCellDouble(Grids_AbstractGridChunk chunk, int chunkRow,
            int chunkCol, int cellRow, int cellCol) {
        Grids_AbstractGridChunkDouble c;
        c = (Grids_AbstractGridChunkDouble) chunk;
        Grids_GridDouble g;
        g = (Grids_GridDouble) c.getGrid();
        if (chunk.getClass() == Grids_GridChunkDoubleArray.class) {
            Grids_GridChunkDoubleArray gridChunkArray;
            gridChunkArray = (Grids_GridChunkDoubleArray) c;
            return gridChunkArray.getCell(cellRow, cellCol);
        }
        if (chunk.getClass() == Grids_GridChunkDoubleMap.class) {
            Grids_GridChunkDoubleMap gridChunkMap;
            gridChunkMap = (Grids_GridChunkDoubleMap) c;
            return gridChunkMap.getCell(cellRow, cellCol);
        }
        double noDataValue = g.NoDataValue;
        return noDataValue;
    }

}
