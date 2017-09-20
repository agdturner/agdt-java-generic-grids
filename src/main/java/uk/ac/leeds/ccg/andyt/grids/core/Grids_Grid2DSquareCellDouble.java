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
package uk.ac.leeds.ccg.andyt.grids.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import uk.ac.leeds.ccg.andyt.grids.exchange.Grids_ESRIAsciiGridImporter;
import uk.ac.leeds.ccg.andyt.grids.utilities.Grids_Utilities;

/**
 * A class for representing grids of double precision values.
 *
 * @see Grids_AbstractGrid2DSquareCell
 */
public class Grids_Grid2DSquareCellDouble
        extends Grids_AbstractGrid2DSquareCell
        implements Serializable {

    /**
     * For storing the NODATA value of the grid, which by default is
     * Double.NEGATIVE_INFINITY. N.B. Double.NaN should not be used. N.B. Care
     * should be taken so that _NoDataValue is not a data value.
     */
    protected double _NoDataValue = Double.NEGATIVE_INFINITY;

    /**
     * Creates a new Grid2DSquareCellDouble
     */
    public Grids_Grid2DSquareCellDouble() {
    }

    /**
     * Creates a new Grid2DSquareCellDouble
     *
     * @param ge
     */
    public Grids_Grid2DSquareCellDouble(
            Grids_Environment ge) {
        initGrid2DSquareCellDouble(
                ge);
    }

    /**
     * Creates a new Grid2DSquareCellDouble. Warning!! Concurrent modification
     * may occur if _Directory is in use. If a completely new instance is wanted
     * then use: Grid2DSquareCellDouble( File, Grid2DSquareCellDoubleAbstract,
     * Grids_AbstractGrid2DSquareCellDoubleChunkFactory, int, int, long, long,
     * long, long, double, HashSet) which can be accessed via a
     * Grids_Grid2DSquareCellDoubleFactory.
     *
     * @param directory The File _Directory to be used for swapping.
     * @param gridFile The File _Directory containing the File names thisFile
     * that the ois was constructed from.
     * @param ois The ObjectInputStream used in first attempt to construct this.
     * @param ge
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    protected Grids_Grid2DSquareCellDouble(
            File directory,
            File gridFile,
            ObjectInputStream ois,
            Grids_Environment ge,
            boolean handleOutOfMemoryError) {
        initGrid2DSquareCellDouble(
                directory,
                gridFile,
                ois,
                ge,
                handleOutOfMemoryError);
    }

    /**
     * Creates a new Grid2DSquareCellDouble with each cell value equal to
     * _NoDataValue and all chunks of the same type.
     *
     * @param gs The AbstractGridStatistics to accompany this.
     * @param directory The File _Directory to be used for swapping.
     * @param chunkFactory The Grids_AbstractGrid2DSquareCellDoubleChunkFactory
     * prefered for creating chunks.
     * @param chunkNRows The number of rows of cells in any chunk.
     * @param chunkNCols The number of columns of cells in any chunk.
     * @param nRows The number of rows of cells.
     * @param nCols The number of columns of cells.
     * @param dimensions The cellsize, xmin, ymin, xmax and ymax.
     * @param noDataValue The _NoDataValue.
     * @param ge
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    protected Grids_Grid2DSquareCellDouble(
            Grids_AbstractGridStatistics gs,
            File directory,
            Grids_AbstractGrid2DSquareCellDoubleChunkFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long nRows,
            long nCols,
            BigDecimal[] dimensions,
            double noDataValue,
            Grids_Environment ge,
            boolean handleOutOfMemoryError) {
        initGrid2DSquareCellDouble(
                gs,
                directory,
                chunkFactory,
                chunkNRows,
                chunkNCols,
                nRows,
                nCols,
                dimensions,
                noDataValue,
                ge,
                handleOutOfMemoryError);
    }

    /**
     * Creates a new Grid2DSquareCellDouble based on values in Grid2DSquareCell.
     *
     * @param gs The AbstractGridStatistics to accompany this.
     * @param directory The File _Directory to be used for swapping.
     * @param grid The Grids_AbstractGrid2DSquareCell from which this is to be
     * constructed.
     * @param chunkFactory The Grids_AbstractGrid2DSquareCellDoubleChunkFactory
     * prefered to construct chunks of this.
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
     * @param noDataValue The _NoDataValue for this.
     * @param ge
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    protected Grids_Grid2DSquareCellDouble(
            Grids_AbstractGridStatistics gs,
            File directory,
            Grids_AbstractGrid2DSquareCell grid,
            Grids_AbstractGrid2DSquareCellDoubleChunkFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRowIndex,
            long startColIndex,
            long endRowIndex,
            long endColIndex,
            double noDataValue,
            Grids_Environment ge,
            boolean handleOutOfMemoryError) {
        initGrid2DSquareCellDouble(
                gs,
                directory,
                grid,
                chunkFactory,
                chunkNRows,
                chunkNCols,
                startRowIndex,
                startColIndex,
                endRowIndex,
                endColIndex,
                noDataValue,
                ge,
                handleOutOfMemoryError);
    }

    /**
     * Creates a new Grid2DSquareCellDouble with values obtained from gridFile.
     * Currently gridFile must be a _Directory of a Grid2DSquareCellDouble or
     * Grids_Grid2DSquareCellInt or a ESRI Asciigrid format file with a _Name
     * ending ".asc".
     *
     * @param gs The AbstractGridStatistics to accompany this.
     * @param directory The File _Directory to be used for swapping.
     * @param gridFile Either a _Directory, or a formatted File with a specific
     * extension containing the data and information about the
     * Grid2DSquareCellDouble to be returned.
     * @param chunkFactory The Grids_AbstractGrid2DSquareCellDoubleChunkFactory
     * prefered to construct chunks of this.
     * @param chunkNRows
     * @param startRowIndex The Grid2DSquareCell row index which is the bottom
     * most row of this.
     * @param chunkNCols
     * @param startColIndex The Grid2DSquareCell column index which is the left
     * most column of this.
     * @param endRowIndex The Grid2DSquareCell row index which is the top most
     * row of this.
     * @param endColIndex The Grid2DSquareCell column index which is the right
     * most column of this.
     * @param noDataValue The _NoDataValue for this.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @param ge
     */
    protected Grids_Grid2DSquareCellDouble(
            Grids_AbstractGridStatistics gs,
            File directory,
            File gridFile,
            Grids_AbstractGrid2DSquareCellDoubleChunkFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRowIndex,
            long startColIndex,
            long endRowIndex,
            long endColIndex,
            double noDataValue,
            Grids_Environment ge,
            boolean handleOutOfMemoryError) {
        initGrid2DSquareCellDouble(
                gs,
                directory,
                gridFile,
                chunkFactory,
                chunkNRows,
                chunkNCols,
                startRowIndex,
                startColIndex,
                endRowIndex,
                endColIndex,
                noDataValue,
                ge,
                handleOutOfMemoryError);
    }

    /**
     * @return a string description of the instance. Basically the values of
     * each field.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    @Override
    public String toString(
            boolean handleOutOfMemoryError) {
        try {
            String result = "Grid2DSquareCellDouble( "
                    + //"_NoDataValue( " + get_NoDataValue( handleOutOfMemoryError) + " ), " +
                    "_NoDataValue( " + this._NoDataValue + " ), "
                    + super.toString(0, handleOutOfMemoryError) + " )";
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                if (env.swapToFile_Grid2DSquareCellChunk_Account() < 1L) {
                    throw a_OutOfMemoryError;
                }
                env.init_MemoryReserve(handleOutOfMemoryError);
                return toString(
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * Initialises this.
     *
     * @see Grid2DSquareCellDouble()
     */
    private void initGrid2DSquareCellDouble(
            Grids_Environment ge) {
        initGrid2DSquareCell(ge);
        this._NoDataValue = Double.NEGATIVE_INFINITY;
    }

    /**
     * Initialises this.
     *
     * @param g The Grids_Grid2DSquareCellDouble from
     * which the fields of this are set.
     * @param initTransientFields Iff true then transient fields of this are set
     * with those of _Grid2DSquareCellDouble.
     */
    private void initGrid2DSquareCellDouble(
            Grids_Grid2DSquareCellDouble g,
            boolean initTransientFields) {
        this._NoDataValue = g._NoDataValue;
        super.initGrid2DSquareCell(g);
        if (initTransientFields) {
            this.env = g.env;
            this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap
                    = g._ChunkID_AbstractGrid2DSquareCellChunk_HashMap;
            // Set the reference to this in the Grid Statistics
            this._GridStatistics.init(this);
            //init_Grid2DSquareCells_MemoryReserve(a_Grid2DSquareCellDouble.env);
            //this._AbstractGrid2DSquareCell_HashSet =
            //        _Grid2DSquareCellDouble._AbstractGrid2DSquareCell_HashSet;
            //this._MemoryReserve =
            //        _Grid2DSquareCellDouble._MemoryReserve;
        }
        this.env._AbstractGrid2DSquareCell_HashSet.add(this);
    }

    /**
     * Initialises this.
     *
     * @param _Directory The File _Directory to be used for swapping.
     * @param gridFile The File _Directory containing the File named thisFile
     * that the ois was constructed from.
     * @param ois The ObjectInputStream used in first attempt to construct this.
     * @param _AbstractGrid2DSquareCell_HashSet A HashSet of swappable
     * Grids_AbstractGrid2DSquareCell instances.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @see Grid2DSquareCellDouble( File, File, ObjectInputStream, HashSet,
     * boolean)
     */
    private void initGrid2DSquareCellDouble(
            File directory_File,
            File grid_File,
            ObjectInputStream ois,
            Grids_Environment ge,
            boolean handleOutOfMemoryError) {
        try {
            //init_Grid2DSquareCells_MemoryReserve(env);
            this.env = ge;
            this._Directory = directory_File;
            File thisFile = new File(
                    grid_File,
                    "thisFile");
            try {
                boolean initTransientFields = false;
                initGrid2DSquareCellDouble((Grids_Grid2DSquareCellDouble) ois.readObject(),
                        initTransientFields);
                ois.close();
                // Set the reference to this in the Grid Chunks
                initGrid2DSquareCellChunks(grid_File);
                Iterator<Grids_AbstractGrid2DSquareCellChunk> chunkIterator;
                chunkIterator = this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.values().iterator();
                while (chunkIterator.hasNext()) {
                    Grids_AbstractGrid2DSquareCellChunk grid2DSquareCellChunkAbstract = chunkIterator.next();
                    grid2DSquareCellChunkAbstract._Grid2DSquareCell = this;
                }
            } catch (ClassCastException e) {
                try {
                    ois.close();
                    ois = new ObjectInputStream(
                            new BufferedInputStream(
                                    new FileInputStream(thisFile)));
                    // If the object is a Grids_Grid2DSquareCellInt
                    Grids_Grid2DSquareCellIntFactory grid2DSquareCellIntFactory = new Grids_Grid2DSquareCellIntFactory(
                            _Directory,
                            env,
                            handleOutOfMemoryError);
                    Grids_Grid2DSquareCellInt grid2DSquareCellInt = (Grids_Grid2DSquareCellInt) grid2DSquareCellIntFactory.create(
                            _Directory,
                            grid_File,
                            ois);
                    Grids_Grid2DSquareCellDoubleFactory grid2DSquareCellDoubleFactory = new Grids_Grid2DSquareCellDoubleFactory(
                            _Directory,
                            env,
                            handleOutOfMemoryError);
                    Grids_Grid2DSquareCellDouble grid2DSquareCellDouble = (Grids_Grid2DSquareCellDouble) grid2DSquareCellDoubleFactory.create(grid2DSquareCellInt);
                    boolean initTransientFields = false;
                    initGrid2DSquareCellDouble(
                            grid2DSquareCellDouble,
                            initTransientFields);
                    initGrid2DSquareCellChunks(grid_File);
                } catch (IOException ioe) {
                    //ioe.printStackTrace();
                    System.err.println(ioe.getLocalizedMessage());
                }
            } catch (ClassNotFoundException e) {
                //ioe.printStackTrace();
                System.err.println(e.getLocalizedMessage());
            } catch (IOException e) {
                //ioe.printStackTrace();
                System.err.println(e.getLocalizedMessage());
            }
            // Set the reference to this in the Grid Statistics
            this._GridStatistics.init(this);
            this.env._AbstractGrid2DSquareCell_HashSet.add(this);
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                ge.clear_MemoryReserve();
                env = ge;
                if (env.swapToFile_Grid2DSquareCellChunks_Account() < 1L) {
                    throw a_OutOfMemoryError;
                }
                initGrid2DSquareCellDouble(_Directory,
                        grid_File,
                        ois,
                        env,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * Initialises this.
     *
     * @param gs The AbstractGridStatistics to accompany this.
     * @param directory The File _Directory to be used for swapping.
     * @param grid2DSquareCellDoubleChunkFactory The
     * Grids_AbstractGrid2DSquareCellDoubleChunkFactory prefered for creating
     * chunks.
     * @param chunkNRows The number of rows of cells in any chunk.
     * @param chunkNCols The number of columns of cells in any chunk.
     * @param nRows The number of rows of cells.
     * @param nCols The number of columns of cells.
     * @param _Dimensions The cellsize, xmin, ymin, xmax and ymax.
     * @param noDataValue The _NoDataValue.
     * @param _AbstractGrid2DSquareCell_HashSet A HashSet of swappable
     * Grids_AbstractGrid2DSquareCell instances.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @see Grid2DSquareCellDouble( AbstractGridStatistics, File,
     * AbstractGrid2DSquareCellDoubleChunkFactory, int, int, long, long ,
     * BigDecimal[], double, HashSet, boolean );
     */
    private void initGrid2DSquareCellDouble(
            Grids_AbstractGridStatistics gs,
            File directory,
            Grids_AbstractGrid2DSquareCellDoubleChunkFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long nRows,
            long nCols,
            BigDecimal[] dimensions,
            double noDataValue,
            Grids_Environment ge,
            boolean handleOutOfMemoryError) {
        try {
            this.env = ge;
            //init_Grid2DSquareCells_MemoryReserve(_Grid2DSquareCells);
            this._Directory = directory;
            this._GridStatistics = gs;
            // Set the reference to this in the Grid Statistics
            this._GridStatistics.init(this);
            //this._GridStatistics.Grid2DSquareCell = this;
            this._Directory = directory;
            this._ChunkNRows = chunkNRows;
            this._ChunkNCols = chunkNCols;
            this._NRows = nRows;
            this._NCols = nCols;
            init_Dimensions(dimensions);
            init_NoDataValue(noDataValue);
            this._Name = directory.getName();
            init_NChunkRows();
            init_NChunkCols();
            long nChunks = getNChunks();
            this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap = new HashMap((int) nChunks);
            int chunkRowIndex = Integer.MIN_VALUE;
            int chunkColIndex = Integer.MIN_VALUE;
            //int loadedChunkCount = 0;
            boolean isLoadedChunk = false;
            int int_0 = 0;
            String println0 = env.initString(1000, handleOutOfMemoryError);
            String println = env.initString(1000, handleOutOfMemoryError);
            Grids_2D_ID_int chunkID = new Grids_2D_ID_int();
            Grids_AbstractGrid2DSquareCellDoubleChunk _Grid2DSquareCellDoubleChunk = chunkFactory.createGrid2DSquareCellDoubleChunk(
                    this,
                    chunkID);
            for (chunkRowIndex = int_0; chunkRowIndex < this._NChunkRows; chunkRowIndex++) {
                for (chunkColIndex = int_0; chunkColIndex < this._NChunkCols; chunkColIndex++) {
                    do {
                        try {
                            // Try to load chunk.
                            chunkID = new Grids_2D_ID_int(
                                    chunkRowIndex,
                                    chunkColIndex);
                            _Grid2DSquareCellDoubleChunk = chunkFactory.createGrid2DSquareCellDoubleChunk(
                                    this,
                                    chunkID);
                            this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.put(
                                    chunkID,
                                    _Grid2DSquareCellDoubleChunk);
                            isLoadedChunk = true;
                        } catch (OutOfMemoryError a_OutOfMemoryError) {
                            if (handleOutOfMemoryError) {
                                env.clear_MemoryReserve();
                                freeSomeMemoryAndResetReserve(chunkRowIndex, chunkColIndex, a_OutOfMemoryError);
                            } else {
                                throw a_OutOfMemoryError;
                            }
                        }
                    } while (!isLoadedChunk);
                    isLoadedChunk = false;
                    //loadedChunkCount++;
                }
                println = "Done chunkRow " + chunkRowIndex + " out of " + this._NChunkRows;
                println = env.println(println, println0, handleOutOfMemoryError);
            }
            this.env._AbstractGrid2DSquareCell_HashSet.add(this);
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                ge.clear_MemoryReserve();
                freeSomeMemoryAndResetReserve(e);
                initGrid2DSquareCellDouble(gs,
                        directory,
                        chunkFactory,
                        chunkNRows,
                        chunkNCols,
                        nRows,
                        nCols,
                        dimensions,
                        noDataValue,
                        env,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * Initialises this.
     *
     * @param gs The AbstractGridStatistics to accompany this.
     * @param directory gridStatistics File _Directory to be used for swapping.
     * @param grid The Grids_AbstractGrid2DSquareCell from which this is to be
     * constructed.
     * @param chunkFactory The Grids_AbstractGrid2DSquareCellDoubleChunkFactory
     * prefered to construct chunks of this.
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
     * @param noDataValue The _NoDataValue for this.
     * @param _AbstractGrid2DSquareCell_HashSet A HashSet of swappable
     * Grids_AbstractGrid2DSquareCell instances.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @see initGrid2DSquareCellDouble( AbstractGridStatistics, File,
     * AbstractGrid2DSquareCell, AbstractGrid2DSquareCellDoubleChunkFactory,
     * int, int, long, long, long, long, double, HashSet, boolean );
     */
    private void initGrid2DSquareCellDouble(
            Grids_AbstractGridStatistics gs,
            File directory,
            Grids_AbstractGrid2DSquareCell grid,
            Grids_AbstractGrid2DSquareCellDoubleChunkFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRowIndex,
            long startColIndex,
            long endRowIndex,
            long endColIndex,
            double noDataValue,
            Grids_Environment ge,
            boolean handleOutOfMemoryError) {
        try {
            env = ge;
            //init_Grid2DSquareCells_MemoryReserve(_Grid2DSquareCells);
            this._Directory = directory;
            this._ChunkNRows = chunkNRows;
            this._ChunkNCols = chunkNCols;
            this._NRows = endRowIndex - startRowIndex + 1L;
            this._NCols = endColIndex - startColIndex + 1L;
            init_NoDataValue(noDataValue);
            //this._AbstractGrid2DSquareCell_HashSet = _AbstractGrid2DSquareCell_HashSet;
            this._Name = directory.getName();
            BigDecimal[] dimensions = grid.get_Dimensions(handleOutOfMemoryError);
            double gridNoDataValue = noDataValue;
            if (grid instanceof Grids_Grid2DSquareCellDouble) {
                gridNoDataValue = ((Grids_Grid2DSquareCellDouble) grid).get_NoDataValue(handleOutOfMemoryError);
            } else if (grid instanceof Grids_Grid2DSquareCellInt) {
                gridNoDataValue = ((Grids_Grid2DSquareCellInt) grid)._NoDataValue;
            }
            this._Dimensions = new BigDecimal[dimensions.length];
            init_NChunkRows();
            init_NChunkCols();
            long nChunks = getNChunks();
            this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap = new HashMap((int) nChunks);
            this._Dimensions[0] = new BigDecimal(dimensions[0].toString());
            BigDecimal startColIndexBigDecimal = new BigDecimal((long) startColIndex);
            BigDecimal startRowIndexBigDecimal = new BigDecimal((long) startRowIndex);
            BigDecimal _NRowsBigDecimal = new BigDecimal(Long.toString(_NRows));
            BigDecimal _NColsBigDecimal = new BigDecimal(Long.toString(_NCols));
            this._Dimensions[1] = dimensions[1].add(startColIndexBigDecimal.multiply(this._Dimensions[0]));
            this._Dimensions[2] = dimensions[2].add(startRowIndexBigDecimal.multiply(this._Dimensions[0]));
            this._Dimensions[3] = this._Dimensions[1].add(_NColsBigDecimal.multiply(this._Dimensions[0]));
            this._Dimensions[4] = this._Dimensions[2].add(_NRowsBigDecimal.multiply(this._Dimensions[0]));
            this._GridStatistics = gs;
            // Set the reference to this in the Grid Statistics
            this._GridStatistics.init(this);
            //this._GridStatistics.Grid2DSquareCell = this;
            int chunkRowIndex;
            int chunkColIndex;
            //int loadedChunkCount = 0;
            boolean isLoadedChunk = false;
            int chunkCellRowIndex;
            int chunkCellColIndex;
            long row = 0;
            long col = 0;
            //int cci1 = 0;
            double cellDouble;
            String println0 = env.initString(1000, handleOutOfMemoryError);
            String println = env.initString(1000, handleOutOfMemoryError);
            Grids_2D_ID_int chunkID = new Grids_2D_ID_int();
            Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellDoubleChunk = chunkFactory.createGrid2DSquareCellDoubleChunk(
                    this,
                    chunkID);
            Grids_AbstractGrid2DSquareCellChunk gridChunk;

            int gridChunkNRows = grid.get_ChunkNRows();
            int gridChunkNCols = grid.get_ChunkNCols();
            int gridNChunkRows = grid.get_NChunkRows();
            int gridNChunkCols = grid.get_NChunkCols();
            int gridNrowsInChunk;
            int gridNcolsInChunk;
            long rowIndex;
            long colIndex;

            int startChunkRowIndex;
            startChunkRowIndex = grid.getChunkRowIndex(startRowIndex);
            int endChunkRowIndex;
            endChunkRowIndex = grid.getChunkRowIndex(endRowIndex);
            int nChunkRows;
            nChunkRows = endChunkRowIndex - startChunkRowIndex + 1;
            int chunkRow = 0;

            int startChunkColIndex;
            startChunkColIndex = grid.getChunkColIndex(startColIndex);
            int endChunkColIndex;
            endChunkColIndex = grid.getChunkColIndex(endColIndex);

            if (gs.getClass() == Grids_GridStatistics0.class) {
                for (chunkRowIndex = startChunkRowIndex; chunkRowIndex <= endChunkRowIndex; chunkRowIndex++) {
                    for (chunkColIndex = startChunkColIndex; chunkColIndex <= endChunkColIndex; chunkColIndex++) {
                        do {
                            try {
                                // Try to load chunk.
                                chunkID = new Grids_2D_ID_int(
                                        chunkRowIndex,
                                        chunkColIndex);
                                grid2DSquareCellDoubleChunk = chunkFactory.createGrid2DSquareCellDoubleChunk(
                                        this,
                                        chunkID);
                                this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.put(
                                        chunkID,
                                        grid2DSquareCellDoubleChunk);
                                gridNrowsInChunk = grid.getChunkNRows(chunkID, handleOutOfMemoryError);
                                gridNcolsInChunk = grid.getChunkNCols(chunkID, handleOutOfMemoryError);
                                for (chunkCellRowIndex = 0; chunkCellRowIndex < gridNrowsInChunk; chunkCellRowIndex++) {
                                    rowIndex = grid.getCellRowIndex(chunkRowIndex, chunkCellRowIndex, chunkID, handleOutOfMemoryError);
                                    if (rowIndex >= startRowIndex && rowIndex <= endRowIndex) {
                                        col = 0;
                                        for (chunkCellColIndex = 0; chunkCellColIndex < gridNcolsInChunk; chunkCellColIndex++) {
                                            colIndex = grid.getCellColIndex(chunkColIndex, chunkCellColIndex, chunkID, handleOutOfMemoryError);
                                            if (colIndex >= startColIndex && colIndex <= endColIndex) {
                                                cellDouble = grid.getCellDouble(
                                                        rowIndex,
                                                        colIndex);
                                                if (cellDouble == gridNoDataValue) {
                                                    initCell(
                                                            row,
                                                            col,
                                                            noDataValue);
                                                } else {
                                                    initCell(
                                                            row,
                                                            col,
                                                            cellDouble);
                                                }
                                                col++;
                                            }
                                        }
                                        row++;
                                    }
                                }
                                isLoadedChunk = true;
                            } catch (OutOfMemoryError a_OutOfMemoryError) {
                                if (handleOutOfMemoryError) {
                                    env.clear_MemoryReserve();
                                    freeSomeMemoryAndResetReserve(chunkID, a_OutOfMemoryError);
                                    chunkID = new Grids_2D_ID_int(
                                            chunkRowIndex,
                                            chunkColIndex);
                                    if (env.swapToFile_Grid2DSquareCellChunksExcept_Account(this, chunkID) < 1L) { // Should also not swap out the chunk of grid thats values are being used to initialise this.
                                        throw a_OutOfMemoryError;
                                    }
                                    env.init_MemoryReserve(
                                            this,
                                            chunkID,
                                            handleOutOfMemoryError);
                                } else {
                                    throw a_OutOfMemoryError;
                                }
                            }
                        } while (!isLoadedChunk);
                        isLoadedChunk = false;
                        //loadedChunkCount++;
                        //cci1 = _ChunkColIndex;
                    }
                    println = "Done chunkRow " + chunkRow + " out of " + nChunkRows;
                    println = env.println(println, println0, handleOutOfMemoryError);
                    chunkRow ++;
                }
            } else {
                for (chunkRowIndex = startChunkRowIndex; chunkRowIndex <= endChunkRowIndex; chunkRowIndex++) {
                    for (chunkColIndex = startChunkColIndex; chunkColIndex <= endChunkColIndex; chunkColIndex++) {
                        do {
                            try {
                                // Try to load chunk.
                                chunkID = new Grids_2D_ID_int(
                                        chunkRowIndex,
                                        chunkColIndex);
                                grid2DSquareCellDoubleChunk = chunkFactory.createGrid2DSquareCellDoubleChunk(
                                        this,
                                        chunkID);
                                this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.put(
                                        chunkID,
                                        grid2DSquareCellDoubleChunk);
                                gridNrowsInChunk = grid.getChunkNRows(chunkID, handleOutOfMemoryError);
                                gridNcolsInChunk = grid.getChunkNCols(chunkID, handleOutOfMemoryError);
                                for (chunkCellRowIndex = 0; chunkCellRowIndex < gridNrowsInChunk; chunkCellRowIndex++) {
                                    rowIndex = grid.getCellRowIndex(chunkRowIndex, chunkCellRowIndex, chunkID, handleOutOfMemoryError);
                                    if (rowIndex >= startRowIndex && rowIndex <= endRowIndex) {
                                        col = 0;
                                        for (chunkCellColIndex = 0; chunkCellColIndex < gridNcolsInChunk; chunkCellColIndex++) {
                                            colIndex = grid.getCellColIndex(chunkColIndex, chunkCellColIndex, chunkID, handleOutOfMemoryError);
                                            if (colIndex >= startColIndex && colIndex <= endColIndex) {
                                                cellDouble = grid.getCellDouble(
                                                        rowIndex,
                                                        colIndex);
                                                setCell(
                                                    row,
                                                    col,
                                                    cellDouble,
                                                    handleOutOfMemoryError);
                                                col++;
                                            }
                                        }
                                        row++;
                                    }
                                }
                                isLoadedChunk = true;
                            } catch (OutOfMemoryError a_OutOfMemoryError) {
                                if (handleOutOfMemoryError) {
                                    env.clear_MemoryReserve();
                                    chunkID = new Grids_2D_ID_int(
                                            chunkRowIndex,
                                            chunkColIndex);
                                    if (env.swapToFile_Grid2DSquareCellChunksExcept_Account(this, chunkID) < 1L) { // Should also not swap out the chunk of grid thats values are being used to initialise this.
                                        throw a_OutOfMemoryError;
                                    }
                                    env.init_MemoryReserve(
                                            this,
                                            chunkID,
                                            handleOutOfMemoryError);
                                } else {
                                    throw a_OutOfMemoryError;
                                }
                            }
                        } while (!isLoadedChunk);
                        isLoadedChunk = false;
                        //loadedChunkCount++;
                        //cci1 = _ChunkColIndex;
                    }
                    println = "Done chunkRow " + chunkRow + " out of " + nChunkRows;
                    println = env.println(println, println0, handleOutOfMemoryError);
                    chunkRow ++;
                }
            }
            this.env._AbstractGrid2DSquareCell_HashSet.add(this);
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                ge.clear_MemoryReserve();
                if (ge.swapToFile_Grid2DSquareCellChunks_Account() < 1) {
                    throw a_OutOfMemoryError;
                }
                ge.init_MemoryReserve(handleOutOfMemoryError);
                initGrid2DSquareCellDouble(
                        gs,
                        directory,
                        grid,
                        chunkFactory,
                        chunkNRows,
                        chunkNCols,
                        startRowIndex,
                        startColIndex,
                        endRowIndex,
                        endColIndex,
                        noDataValue,
                        ge,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * Initialises this.
     *
     * @param gridStatistics The AbstractGridStatistics to accompany this.
     * @param directory The File _Directory to be used for swapping.
     * @param gridFile Either a _Directory, or a formatted File with a specific
     * extension containing the data and information about the
     * Grids_Grid2DSquareCellDouble to be returned.
     * @param chunkFactory The Grids_AbstractGrid2DSquareCellDoubleChunkFactory
     * prefered to construct chunks of this.
     * @param chunkNRows The Grids_Grid2DSquareCellDouble _ChunkNRows.
     * @param chunkNCols The Grids_Grid2DSquareCellDouble _ChunkNCols.
     * @param startRowIndex The topmost row index of the grid stored as
     * gridFile.
     * @param startColIndex The leftmost column index of the grid stored as
     * gridFile.
     * @param endRowIndex The bottom row index of the grid stored as gridFile.
     * @param endColIndex The rightmost column index of the grid stored as
     * gridFile.
     * @param noDataValue The _NoDataValue for this.
     * @param _AbstractGrid2DSquareCell_HashSet A HashSet of swappable
     * Grids_AbstractGrid2DSquareCell instances.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @see this.Grid2DSquareCellDouble( AbstractGridStatistics, File, File,
     * AbstractGrid2DSquareCellDoubleChunkFactory, int, int, long, long, long,
     * long, double, HashSet, boolean )
     */
    private void initGrid2DSquareCellDouble(
            Grids_AbstractGridStatistics gridStatistics,
            File directory,
            File gridFile,
            Grids_AbstractGrid2DSquareCellDoubleChunkFactory chunkFactory,
            int chunkNRows,
            int chunkNCols,
            long startRowIndex,
            long startColIndex,
            long endRowIndex,
            long endColIndex,
            double noDataValue,
            Grids_Environment grids_Environment,
            boolean handleOutOfMemoryError) {
        try {
            this.env = grids_Environment;
            // Setting _Directory allows for it having being moved.
            this._Directory = directory;
            String println0 = env.initString(1000, env.HandleOutOfMemoryErrorFalse);
            String println = env.initString(1000, env.HandleOutOfMemoryErrorFalse);
            // Set to report every 10%
            int reportN;
            reportN = (int) (endRowIndex - startRowIndex) / 10;
            if (gridFile.isDirectory()) {
                /* Initialise from
                 * new File(
                 *     gridFile,
                 *     "thisFile" );
                 */
                if (true) {
                    Grids_Grid2DSquareCellDoubleFactory grid2DSquareCellDoubleFactory = new Grids_Grid2DSquareCellDoubleFactory(
                            directory,
                            chunkNRows,
                            chunkNCols,
                            chunkFactory,
                            noDataValue,
                            env,
                            handleOutOfMemoryError);
                    File thisFile = new File(
                            gridFile,
                            "thisFile");
                    ObjectInputStream ois = null;
                    try {
                        ois = new ObjectInputStream(
                                new BufferedInputStream(
                                        new FileInputStream(thisFile)));
                        Grids_Grid2DSquareCellDouble gridFileGrid2DSquareCellDouble = (Grids_Grid2DSquareCellDouble) grid2DSquareCellDoubleFactory.create(
                                directory,
                                thisFile,
                                ois);
                        Grids_Grid2DSquareCellDouble gridFileGrid2DSquareCell = grid2DSquareCellDoubleFactory.create(directory,
                                gridFileGrid2DSquareCellDouble,
                                startRowIndex,
                                startColIndex,
                                endRowIndex,
                                endColIndex,
                                env,
                                handleOutOfMemoryError);
                        initGrid2DSquareCellDouble(
                                gridFileGrid2DSquareCell,
                                false);
                    } catch (IOException ioe0) {
                        System.err.println(ioe0.getMessage());
                        //ioe0.printStackTrace();
                        //throw ioe0;
                    }
                }
                initGrid2DSquareCellChunks(gridFile);
            } else {
                // Assume ESRI AsciiFile
                this._ChunkNRows = chunkNRows;
                this._ChunkNCols = chunkNCols;
                this._NRows = endRowIndex - startRowIndex + 1L;
                this._NCols = endColIndex - startColIndex + 1L;
                init_NoDataValue(noDataValue);
                this.env = grids_Environment;
                this._Name = directory.getName();
                init_NChunkRows();
                init_NChunkCols();
                long nChunks = getNChunks();
                this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap = new HashMap((int) nChunks);
                this._Dimensions = new BigDecimal[5];
                this._GridStatistics = gridStatistics;
                // Set the reference to this in the Grid Statistics
                this._GridStatistics.init(this);
                //this._GridStatistics.Grid2DSquareCell = this;
                String filename = gridFile.getName();
                int loadedChunkCount = 0;
                boolean isLoadedChunk = false;
                double value = this._NoDataValue;
                if (filename.endsWith("asc") || filename.endsWith("txt")) {
                    Grids_ESRIAsciiGridImporter _ESRIAsciigridImporter = new Grids_ESRIAsciiGridImporter(
                            gridFile,
                            env);
                    Object[] header = _ESRIAsciigridImporter.readHeaderObject();
                    //long inputNcols = ( Long ) header[ 0 ];
                    //long inputNrows = ( Long ) header[ 1 ];
                    this._Dimensions[0] = (BigDecimal) header[4];
                    this._Dimensions[1] = ((BigDecimal) header[2]).add(_Dimensions[0].multiply(new BigDecimal(startColIndex)));
                    this._Dimensions[2] = ((BigDecimal) header[3]).add(_Dimensions[0].multiply(new BigDecimal(startRowIndex)));
                    this._Dimensions[3] = this._Dimensions[1].add(new BigDecimal(Long.toString(this._NCols)).multiply(this._Dimensions[0]));
                    this._Dimensions[4] = this._Dimensions[2].add(new BigDecimal(Long.toString(this._NRows)).multiply(this._Dimensions[0]));
                    init_Dimensions(_Dimensions);
                    double gridFileNoDataValue = (Double) header[5];
                    int chunkRowIndex = Integer.MIN_VALUE;
                    int chunkColIndex = Integer.MIN_VALUE;
                    int cachedAndClearedChunkCount = 0;
                    int cri0 = 0;
                    int cci0 = 0;
                    int cri1 = 0;
                    int cci1 = 0;
                    Grids_2D_ID_int chunkID = new Grids_2D_ID_int();
                    boolean initialisedChunksToClear = true;
                    long row = Long.MIN_VALUE;
                    long col = Long.MIN_VALUE;
                    boolean isInitCellDone = false;
                    //boolean cacheRowMinor = true;
                    //ChunkID nextChunkToSwap = new Grids_2D_ID_int(
                    //        this._NChunkCols,
                    //        0,
                    //        0 );
                    Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellDoubleChunk
                            = chunkFactory.createGrid2DSquareCellDoubleChunk(
                                    this,
                                    chunkID);
                    // Initialise Chunks
                    for (chunkRowIndex = 0; chunkRowIndex < _NChunkRows; chunkRowIndex++) {
                        //for ( _ChunkRowIndex = _NChunkRows - 1; _ChunkRowIndex >= 0; _ChunkRowIndex -- ) {
                        for (chunkColIndex = 0; chunkColIndex < _NChunkCols; chunkColIndex++) {
                            do {
                                try {
                                    chunkID = new Grids_2D_ID_int(
                                            chunkRowIndex,
                                            chunkColIndex);
                                    grid2DSquareCellDoubleChunk
                                            = chunkFactory.createGrid2DSquareCellDoubleChunk(
                                                    this,
                                                    chunkID);
                                    this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.put(
                                            chunkID,
                                            grid2DSquareCellDoubleChunk);
                                    isLoadedChunk = true;
                                } catch (OutOfMemoryError a_OutOfMemoryError) {
                                    if (handleOutOfMemoryError) {
                                        env.clear_MemoryReserve();
                                        chunkID = new Grids_2D_ID_int(
                                                chunkRowIndex,
                                                chunkColIndex);
                                        if (env.swapToFile_Grid2DSquareCellChunksExcept_Account(this, chunkID) < 1L) {
                                            throw a_OutOfMemoryError;
                                        }
                                        env.init_MemoryReserve(handleOutOfMemoryError);
                                    } else {
                                        throw a_OutOfMemoryError;
                                    }
                                }
                            } while (!isLoadedChunk);
                            isLoadedChunk = false;
                            loadedChunkCount++;
                            cci1 = chunkColIndex;
                        }
                        System.out.println("Done chunkRow " + chunkRowIndex + " out of " + this._NChunkRows);
                    }
                    System.out.println("First stage of initialisation complete. Reading data into initialised Chunks");

                    // Read Data into Chunks
                    if ((int) gridFileNoDataValue == Integer.MIN_VALUE) {
                        if (gridStatistics.getClass() == Grids_GridStatistics0.class) {
                            for (row = (this._NRows - 1); row > -1; row--) {
                                for (col = 0; col < this._NCols; col++) {
                                    value = _ESRIAsciigridImporter.readDouble();
                                    do {
                                        try {
                                            initCell(
                                                    row,
                                                    col,
                                                    value,
                                                    handleOutOfMemoryError);

//                                            if (value != 0.0d) {
//                                                int debug = 1;
//                                            }
                                            isInitCellDone = true;
                                        } catch (OutOfMemoryError a_OutOfMemoryError) {
                                            if (handleOutOfMemoryError) {
                                                env.clear_MemoryReserve();
                                                chunkID = new Grids_2D_ID_int(
                                                        chunkRowIndex,
                                                        chunkColIndex);
                                                if (env.swapToFile_Grid2DSquareCellChunksExcept_Account(this, chunkID) < 1L) {
                                                    throw a_OutOfMemoryError;
                                                }
                                                env.init_MemoryReserve(handleOutOfMemoryError);
                                            } else {
                                                throw a_OutOfMemoryError;
                                            }
                                        }
                                    } while (!isInitCellDone);
                                    isInitCellDone = false;
                                }
                                if (row % reportN == 0) {
                                    System.out.println("Done row " + row);
                                }
                            }
                        } else {
                            // _GridStatistics.getClass() == Grids_GridStatistics1.class
                            for (row = (this._NRows - 1); row > -1; row--) {
                                for (col = 0; col < this._NCols; col++) {
                                    value = _ESRIAsciigridImporter.readDouble();

//                                    if (value != 0.0d) {
//                                        int debug = 1;
//                                    }
                                    do {
                                        try {
                                            initCellFast(
                                                    row,
                                                    col,
                                                    value);
                                            isInitCellDone = true;
                                        } catch (OutOfMemoryError a_OutOfMemoryError) {
                                            if (handleOutOfMemoryError) {
                                                env.clear_MemoryReserve();
                                                chunkID = new Grids_2D_ID_int(
                                                        chunkRowIndex,
                                                        chunkColIndex);
                                                if (env.swapToFile_Grid2DSquareCellChunksExcept_Account(this, chunkID) < 1L) {
                                                    throw a_OutOfMemoryError;
                                                }
                                                env.init_MemoryReserve(
                                                        this,
                                                        chunkID,
                                                        handleOutOfMemoryError);
                                            } else {
                                                throw a_OutOfMemoryError;
                                            }
                                        }
                                    } while (!isInitCellDone);
                                    isInitCellDone = false;
                                }
                                if (row % reportN == 0) {
                                    System.out.println("Done row " + row);
                                }
                            }
                        }
                    } else {
                        if (gridStatistics.getClass() == Grids_GridStatistics0.class) {
                            for (row = (this._NRows - 1); row > -1; row--) {
                                for (col = 0; col < this._NCols; col++) {
                                    value = _ESRIAsciigridImporter.readDouble();

//                                    if (value != 0.0d) {
//                                        int debug = 1;
//                                    }
                                    do {
                                        try {
                                            if (value != gridFileNoDataValue) {
                                                initCell(
                                                        row,
                                                        col,
                                                        value);
                                            } else {
                                                initCell(
                                                        row,
                                                        col,
                                                        this._NoDataValue);
                                            }
                                            isInitCellDone = true;
                                        } catch (OutOfMemoryError a_OutOfMemoryError) {
                                            if (handleOutOfMemoryError) {
                                                env.clear_MemoryReserve();
                                                chunkID = new Grids_2D_ID_int(
                                                        chunkRowIndex,
                                                        chunkColIndex);
                                                if (env.swapToFile_Grid2DSquareCellChunksExcept_Account(this, chunkID) < 1L) {
                                                    throw a_OutOfMemoryError;
                                                }
                                                env.init_MemoryReserve(
                                                        this,
                                                        chunkID,
                                                        handleOutOfMemoryError);
                                            } else {
                                                throw a_OutOfMemoryError;
                                            }
                                        }
                                    } while (!isInitCellDone);
                                    isInitCellDone = false;
                                }
                                if (row % reportN == 0) {
                                    System.out.println("Done row " + row);
                                }
                            }
                        } else {
                            // _GridStatistics.getClass() == Grids_GridStatistics1.class
                            for (row = (this._NRows - 1); row > -1; row--) {
                                for (col = 0; col < this._NCols; col++) {
                                    value = _ESRIAsciigridImporter.readDouble();

                                    if (value != 0.0d) {
                                        int debug = 1;
                                    }

                                    do {
                                        try {
                                            if (value != gridFileNoDataValue) {
                                                initCellFast(
                                                        row,
                                                        col,
                                                        value);
                                            } else {
                                                initCellFast(
                                                        row,
                                                        col,
                                                        this._NoDataValue);
                                            }
                                            isInitCellDone = true;
                                        } catch (OutOfMemoryError a_OutOfMemoryError) {
                                            if (handleOutOfMemoryError) {
                                                env.clear_MemoryReserve();
                                                chunkID = new Grids_2D_ID_int(
                                                        chunkRowIndex,
                                                        chunkColIndex);
                                                if (env.swapToFile_Grid2DSquareCellChunksExcept_Account(this, chunkID) < 1L) {
                                                    throw a_OutOfMemoryError;
                                                }
                                                env.init_MemoryReserve(
                                                        this,
                                                        chunkID,
                                                        handleOutOfMemoryError);
                                            } else {
                                                throw a_OutOfMemoryError;
                                            }
                                        }
                                    } while (!isInitCellDone);
                                    isInitCellDone = false;
                                }
                                if (row % reportN == 0) {
                                    println = "Done row " + row;
                                    println = env.println(println, println0, handleOutOfMemoryError);
                                }
                            }
                        }
                    }
                }
            }
            this._GridStatistics = gridStatistics;
            // Set the reference to this in the Grid Statistics
            //this._GridStatistics.Grid2DSquareCell = this;
            this._GridStatistics.init(this);
            //this._GridStatistics.Grid2DSquareCell = this;
            this.env._AbstractGrid2DSquareCell_HashSet.add(this);
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                grids_Environment.clear_MemoryReserve();
                if (grids_Environment.swapToFile_Grid2DSquareCellChunks_Account() < 1L) {
                    throw a_OutOfMemoryError;
                }
                grids_Environment.init_MemoryReserve(handleOutOfMemoryError);
                initGrid2DSquareCellDouble(
                        gridStatistics,
                        directory,
                        gridFile,
                        chunkFactory,
                        chunkNRows,
                        chunkNCols,
                        startRowIndex,
                        startColIndex,
                        endRowIndex,
                        endColIndex,
                        noDataValue,
                        grids_Environment,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * @param chunkRowIndex
     * @param chunkColIndex
     * @return grid2DSquareCellDoubleChunks.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public Grids_AbstractGrid2DSquareCellDoubleChunk getGrid2DSquareCellDoubleChunk(
            int chunkRowIndex,
            int chunkColIndex,
            boolean handleOutOfMemoryError) {
        try {
            Grids_AbstractGrid2DSquareCellDoubleChunk result = getGrid2DSquareCellDoubleChunk(
                    chunkRowIndex,
                    chunkColIndex);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();                
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        chunkRowIndex,
                        chunkColIndex);
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getGrid2DSquareCellDoubleChunk(
                        chunkID,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param chunkRowIndex
     * @param chunkColIndex
     * @return grid2DSquareCellDoubleChunks.
     */
    protected Grids_AbstractGrid2DSquareCellDoubleChunk getGrid2DSquareCellDoubleChunk(
            int chunkRowIndex,
            int chunkColIndex) {
        Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                chunkRowIndex,
                chunkColIndex);
        return getGrid2DSquareCellDoubleChunk(chunkID);
    }

    /**
     * @return grid2DSquareCellDoubleChunksAbstract for the given ID
     * @param chunkID
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public Grids_AbstractGrid2DSquareCellDoubleChunk getGrid2DSquareCellDoubleChunk(
            Grids_2D_ID_int chunkID,
            boolean handleOutOfMemoryError) {
        try {
            Grids_AbstractGrid2DSquareCellDoubleChunk result = getGrid2DSquareCellDoubleChunk(chunkID);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getGrid2DSquareCellDoubleChunk(
                        chunkID,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return grid2DSquareCellDoubleChunksAbstract for the given ID
     * @param chunkID
     */
    protected Grids_AbstractGrid2DSquareCellDoubleChunk getGrid2DSquareCellDoubleChunk(
            Grids_2D_ID_int chunkID) {
        boolean containsKey = false;
        boolean isInGrid = isInGrid(chunkID);
        if (isInGrid) {
            containsKey = this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.containsKey(chunkID);
            if (!containsKey) {
                loadIntoCacheChunk(chunkID);
            }
            return (Grids_AbstractGrid2DSquareCellDoubleChunk) this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.get(chunkID);
        }
        return null;
    }

    /**
     * If newValue and oldValue are the same then statistics won't change. A
     * test might be appropriate in set cell so that this method is not called.
     * Also want to keep track if underlying data has changed for getting
     * statistics of Grids_GridStatistics1 type.
     *
     * @param newValue The value replacing oldValue.
     * @param oldValue The value being replaced.
     * @param _NoDataValue this._NoDataValue
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    private void upDateGridStatistics(
            double newValue,
            double oldValue,
            double _NoDataValue,
            boolean handleOutOfMemoryError) {
        try {
            upDateGridStatistics(
                    newValue,
                    oldValue,
                    _NoDataValue);
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                freeSomeMemoryAndResetReserve(handleOutOfMemoryError, e);
                upDateGridStatistics(
                        newValue,
                        oldValue,
                        _NoDataValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * If newValue and oldValue are the same then statistics won't change. A
     * test might be appropriate in set cell so that this method is not called.
     * Also want to keep track if underlying data has changed for getting
     * statistics of Grids_GridStatistics1 type.
     *
     * @param newValue The value replacing oldValue.
     * @param oldValue The value being replaced.
     * @param _NoDataValue this._NoDataValue
     */
    private void upDateGridStatistics(
            double newValue,
            double oldValue,
            double _NoDataValue) {
        if (this._GridStatistics instanceof Grids_GridStatistics0) {
            if (oldValue != _NoDataValue) {
                BigDecimal oldValueBigDecimal = new BigDecimal(oldValue);
                this._GridStatistics.nonNoDataValueCountBigInteger
                        = this._GridStatistics.nonNoDataValueCountBigInteger.subtract(BigInteger.ONE);
                this._GridStatistics.sumBigDecimal
                        = this._GridStatistics.sumBigDecimal.subtract(oldValueBigDecimal);
                if (oldValueBigDecimal.compareTo(this._GridStatistics.minBigDecimal) == 0) {
                    this._GridStatistics.minCountBigInteger
                            = this._GridStatistics.minCountBigInteger.subtract(BigInteger.ONE);
                }
                if (oldValueBigDecimal.compareTo(this._GridStatistics.maxBigDecimal) == 0) {
                    this._GridStatistics.maxCountBigInteger
                            = this._GridStatistics.maxCountBigInteger.subtract(BigInteger.ONE);
                }
            }
            if (newValue != _NoDataValue) {
                BigDecimal newValueBigDecimal = new BigDecimal(newValue);
                this._GridStatistics.nonNoDataValueCountBigInteger
                        = this._GridStatistics.nonNoDataValueCountBigInteger.add(BigInteger.ONE);
                this._GridStatistics.sumBigDecimal
                        = this._GridStatistics.sumBigDecimal.add(newValueBigDecimal);
                if (newValueBigDecimal.compareTo(this._GridStatistics.minBigDecimal) == -1) {
                    this._GridStatistics.minBigDecimal = newValueBigDecimal;
                    this._GridStatistics.minCountBigInteger = BigInteger.ONE;
                } else {
                    if (newValueBigDecimal.compareTo(this._GridStatistics.minBigDecimal) == 0) {
                        this._GridStatistics.minCountBigInteger
                                = this._GridStatistics.minCountBigInteger.add(BigInteger.ONE);
                    } else {
                        if (this._GridStatistics.minCountBigInteger.compareTo(BigInteger.ONE) == -1) {
                            // The GridStatistics need recalculating
                            this._GridStatistics.update();
                        }
                    }
                }
                if (newValueBigDecimal.compareTo(this._GridStatistics.maxBigDecimal) == 1) {
                    this._GridStatistics.maxBigDecimal = newValueBigDecimal;
                    this._GridStatistics.maxCountBigInteger = BigInteger.ONE;
                } else {
                    if (newValueBigDecimal.compareTo(this._GridStatistics.maxBigDecimal) == 0) {
                        this._GridStatistics.maxCountBigInteger
                                = this._GridStatistics.maxCountBigInteger.add(BigInteger.ONE);
                    } else {
                        if (this._GridStatistics.maxCountBigInteger.compareTo(BigInteger.ONE) == -1) {
                            // The GridStatistics need recalculating
                            this._GridStatistics.update();
                        }
                    }
                }
            }
        } else {
            if (newValue != oldValue) {
                ((Grids_GridStatistics1) this._GridStatistics).setIsUpToDate(false);
            }
        }
    }

    /**
     * @return _NoDataValue.
     *
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public final double get_NoDataValue(
            boolean handleOutOfMemoryError) {
        try {
            double result = this._NoDataValue;
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                if (env.swapToFile_Grid2DSquareCellChunk_Account() < 1L) {
                    throw a_OutOfMemoryError;
                }
                env.init_MemoryReserve(handleOutOfMemoryError);
                return get_NoDataValue(
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * @return the _NoDataValue of this as a BigDecimal.
     *
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    @Override
    public BigDecimal getNoDataValueBigDecimal(
            boolean handleOutOfMemoryError) {
        try {
            if (Double.isInfinite(_NoDataValue)) {
                // Cannot convert to a BigDecimal!
                return null;
            }
            BigDecimal result = new BigDecimal(_NoDataValue);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                freeSomeMemoryAndResetReserve(handleOutOfMemoryError, e);
                return getNoDataValueBigDecimal(
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * Initialises _NoDataValue.
     *
     * @param _NoDataValue The value this._NoDataValue is initialised to.
     */
    protected final void init_NoDataValue(
            double _NoDataValue) {
        // This method is called before this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap are
        // constructed so no OutOfMemoryError handling is needed.
        if (Double.isNaN(_NoDataValue)) {
            this._NoDataValue = Double.NEGATIVE_INFINITY;
            System.out.println("_NoDataValue cannot be set to NaN! Initialised _NoDataValue as " + this._NoDataValue);
            //throw new Exception( "_NoDataValue cannot be set to NaN" );
        } else {
            this._NoDataValue = _NoDataValue;
        }
    }

    /**
     * @return Value at _CellRowIndex, _CellColIndex else returns _NoDataValue.
     * @param cellRowIndex .
     * @param cellColIndex .
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getCell(
            long cellRowIndex,
            long cellColIndex,
            boolean handleOutOfMemoryError) {
        try {
            double result = getCell(
                    cellRowIndex,
                    cellColIndex);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, a_OutOfMemoryError);
                return getCell(
                        cellRowIndex,
                        cellColIndex,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * @return Value at _CellRowIndex, _CellColIndex else returns _NoDataValue.
     *
     * @param a_CellRowIndex .
     * @param a_CellColIndex .
     */
    protected double getCell(
            long a_CellRowIndex,
            long a_CellColIndex) {
        boolean isInGrid = isInGrid(
                a_CellRowIndex,
                a_CellColIndex);
        if (isInGrid) {
            long _ChunkNrowsLong = this._ChunkNRows;
            long _ChunkNcolsLong = this._ChunkNCols;
            int _ChunkRowIndex = getChunkRowIndex(a_CellRowIndex);
            int _ChunkColIndex = getChunkColIndex(a_CellColIndex);
            long _ChunkRowIndexLong = _ChunkRowIndex;
            long _ChunkColIndexLong = _ChunkColIndex;
            int _ChunkCellRowIndex = (int) (a_CellRowIndex - (_ChunkRowIndexLong * _ChunkNrowsLong));
            int _ChunkCellColIndex = (int) (a_CellColIndex - (_ChunkColIndexLong * _ChunkNcolsLong));
            Grids_AbstractGrid2DSquareCellChunk grid2DSquareCellChunk = getGrid2DSquareCellChunk(
                    _ChunkRowIndex,
                    _ChunkColIndex);
            if (grid2DSquareCellChunk.getClass() == Grids_Grid2DSquareCellDoubleChunk64CellMap.class) {
                return ((Grids_Grid2DSquareCellDoubleChunk64CellMap) grid2DSquareCellChunk).getCell(
                        _ChunkCellRowIndex,
                        _ChunkCellColIndex,
                        _NoDataValue);
            } else {
                if (grid2DSquareCellChunk.getClass() == Grids_Grid2DSquareCellDoubleChunkArray.class) {
                    return ((Grids_Grid2DSquareCellDoubleChunkArray) grid2DSquareCellChunk).getCell(
                            _ChunkCellRowIndex,
                            _ChunkCellColIndex,
                            _NoDataValue);
                } else {
                    if (grid2DSquareCellChunk.getClass() == Grids_Grid2DSquareCellDoubleChunkJAI.class) {
                        return ((Grids_Grid2DSquareCellDoubleChunkJAI) grid2DSquareCellChunk).getCell(
                                _ChunkCellRowIndex,
                                _ChunkCellColIndex,
                                _NoDataValue);
                    } else {
                        return ((Grids_Grid2DSquareCellDoubleChunkMap) grid2DSquareCellChunk).getCell(
                                _ChunkCellRowIndex,
                                _ChunkCellColIndex,
                                _NoDataValue);
                    }
                }
            }
        }
        return _NoDataValue;
    }

    /**
     * @param grid2DSquareCellChunk
     * @return Value at position given by chunk row index _ChunkRowIndex, chunk
     * column index _ChunkColIndex, chunk cell row index chunkCellRowIndex,
     * chunk cell column index chunkCellColIndex.
     * @param chunkRowIndex The chunk row index of the cell thats value is
     * returned.
     * @param chunkColIndex The chunk column index of the cell thats value is
     * returned.
     * @param chunkCellRowIndex The chunk cell row index of the cell thats value
     * is returned.
     * @param chunkCellColIndex The chunk cell column index of the cell thats
     * value is returned.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getCell(
            Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellChunk,
            int chunkRowIndex,
            int chunkColIndex,
            int chunkCellRowIndex,
            int chunkCellColIndex,
            boolean handleOutOfMemoryError) {
        try {
            double result = getCell(
                    grid2DSquareCellChunk,
                    chunkRowIndex,
                    chunkColIndex,
                    chunkCellRowIndex,
                    chunkCellColIndex);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        chunkRowIndex,
                        chunkColIndex);
                freeSomeMemoryAndResetReserve(chunkID, a_OutOfMemoryError);
                return getCell(
                        grid2DSquareCellChunk,
                        chunkRowIndex,
                        chunkColIndex,
                        chunkCellRowIndex,
                        chunkCellColIndex,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * @param grid2DSquareCellChunk
     * @return Value at position given by chunk row index _ChunkRowIndex, chunk
     * column index _ChunkColIndex, chunk cell row index chunkCellRowIndex,
     * chunk cell column index chunkCellColIndex.
     * @param chunkRowIndex The chunk row index of the cell that's value is
     * returned.
     * @param chunkColIndex The chunk column index of the cell that's value is
     * returned.
     * @param chunkCellRowIndex The chunk cell row index of the cell thats value
     * is returned.
     * @param chunkCellColIndex The chunk cell column index of the cell thats
     * value is returned.
     */
    protected double getCell(
            Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellChunk,
            int chunkRowIndex,
            int chunkColIndex,
            int chunkCellRowIndex,
            int chunkCellColIndex) {
        if (grid2DSquareCellChunk.getClass() == Grids_Grid2DSquareCellDoubleChunk64CellMap.class) {
            return ((Grids_Grid2DSquareCellDoubleChunk64CellMap) grid2DSquareCellChunk).getCell(
                    chunkCellRowIndex,
                    chunkCellColIndex,
                    _NoDataValue);
        } else {
            if (grid2DSquareCellChunk.getClass() == Grids_Grid2DSquareCellDoubleChunkArray.class) {
                return ((Grids_Grid2DSquareCellDoubleChunkArray) grid2DSquareCellChunk).getCell(
                        chunkCellRowIndex,
                        chunkCellColIndex,
                        _NoDataValue);
            } else {
                if (grid2DSquareCellChunk.getClass() == Grids_Grid2DSquareCellDoubleChunkJAI.class) {
                    return ((Grids_Grid2DSquareCellDoubleChunkJAI) grid2DSquareCellChunk).getCell(
                            chunkCellRowIndex,
                            chunkCellColIndex,
                            _NoDataValue);
                } else {
                    if (grid2DSquareCellChunk.getClass() == Grids_Grid2DSquareCellDoubleChunkMap.class) {
                        return ((Grids_Grid2DSquareCellDoubleChunkMap) grid2DSquareCellChunk).getCell(
                                chunkCellRowIndex,
                                chunkCellColIndex,
                                _NoDataValue);
                    } else {
                        return _NoDataValue;
                    }
                }
            }
        }
    }

    /**
     * For returning the value of the cell containing point given by
     * x-coordinate x, y-coordinate y as a double.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public final double getCell(
            double x,
            double y,
            boolean handleOutOfMemoryError) {
        try {
            double result = getCell(
                    x,
                    y);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(y),
                        getChunkColIndex(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getCell(
                        x,
                        y,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * For returning the value of the cell containing point given by
     * x-coordinate x, y-coordinate y as a int.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @return
     */
    protected final double getCell(
            double x,
            double y) {
        return getCell(
                getCellRowIndex(y),
                getCellColIndex(x));
    }

    /**
     * For returning the value of the cell with cell Grids_2D_ID_int cellID as a int.
     *
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public final double getCell(
            Grids_2D_ID_long cellID,
            boolean handleOutOfMemoryError) {
        try {
            double result = getCell(
                    cellID._Row,
                    cellID._Col);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellID._Row),
                        getChunkColIndex(cellID._Col));
                freeSomeMemoryAndResetReserve(chunkID, a_OutOfMemoryError);
                return getCell(
                        cellID,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * For returning the value at x-coordinate x, y-coordinate y and setting it
     * to newValue.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @param newValue .
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public final double setCell(
            double x,
            double y,
            double newValue,
            boolean handleOutOfMemoryError) {
        try {
            double result = setCell(
                    x,
                    y,
                    newValue);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(y),
                        getChunkColIndex(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return setCell(
                        x,
                        y,
                        newValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * For returning the value at x-coordinate x, y-coordinate y and setting it
     * to newValue.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     * @param newValue .
     * @return
     */
    protected final double setCell(
            double x,
            double y,
            double newValue) {
        return setCell(
                getCellRowIndex(x),
                getCellColIndex(y),
                newValue);
    }

    /**
     * For returning the value of the cell with cell Grids_2D_ID_int _CellID and setting it
     * to newValue.
     *
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param newValue .
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public final double setCell(
            Grids_2D_ID_long cellID,
            double newValue,
            boolean handleOutOfMemoryError) {
        try {
            double result = setCell(
                    cellID.getRow(),
                    cellID.getCol(),
                    newValue);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellID.getRow()),
                        getChunkColIndex(cellID.getCol()));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return setCell(
                        cellID,
                        newValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * For returning the value at _CellRowIndex, _CellColIndex and setting it to
     * newValue.
     *
     * @param cellRowIndex
     * @param cellColIndex
     * @param newValue .
     * @return
     */
    @Override
    protected double setCell(
            long cellRowIndex,
            long cellColIndex,
            double newValue) {
        int chunkRowIndex = getChunkRowIndex(cellRowIndex);
        int chunkColIndex = getChunkColIndex(cellColIndex);
        int chunkCellRowIndex = getChunkCellRowIndex(cellRowIndex);
        int chunkCellColIndex = getChunkCellColIndex(cellColIndex);
        //if ( inGrid( _CellRowIndex, _CellColIndex ) ) {
        Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellDoubleChunk = getGrid2DSquareCellDoubleChunk(
                chunkRowIndex,
                chunkColIndex);
        return setCell(
                grid2DSquareCellDoubleChunk,
                chunkRowIndex,
                chunkColIndex,
                chunkCellRowIndex,
                chunkCellColIndex,
                newValue);
        //} else {
        //    return get_NoDataValue();
        //}
    }

    /**
     * For returning the value of the cell in chunk given by _ChunkRowIndex and
     * _ChunkColIndex and cell in the chunk given by chunkCellColIndex and
     * chunkCellRowIndex and setting it to newValue.
     *
     * @param chunkRowIndex
     * @param chunkColIndex
     * @param chunkCellRowIndex
     * @param chunkCellColIndex
     * @param newValue
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @return
     */
    public double setCell(
            int chunkRowIndex,
            int chunkColIndex,
            int chunkCellRowIndex,
            int chunkCellColIndex,
            double newValue,
            boolean handleOutOfMemoryError) {
        try {
            double result = setCell(
                    chunkRowIndex,
                    chunkColIndex,
                    chunkCellRowIndex,
                    chunkCellColIndex,
                    newValue);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        chunkRowIndex,
                        chunkColIndex);
                freeSomeMemoryAndResetReserve(chunkID, e);
                return setCell(
                        chunkRowIndex,
                        chunkColIndex,
                        chunkCellRowIndex,
                        chunkCellColIndex,
                        newValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * For returning the value of the cell in chunk given by _ChunkRowIndex and
     * _ChunkColIndex and cell in the chunk given by chunkCellColIndex and
     * chunkCellRowIndex and setting it to newValue.
     *
     * @param chunkRowIndex
     * @param chunkColIndex
     * @param chunkCellRowIndex
     * @param chunkCellColIndex
     * @param newValue
     * @return
     */
    protected double setCell(
            int chunkRowIndex,
            int chunkColIndex,
            int chunkCellRowIndex,
            int chunkCellColIndex,
            double newValue) {
        //if ( inGrid( _ChunkRowIndex, _ChunkColIndex, chunkCellRowIndex, chunkCellColIndex ) ) {
        Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellDoubleChunk = getGrid2DSquareCellDoubleChunk(
                chunkRowIndex,
                chunkColIndex);
        return setCell(
                grid2DSquareCellDoubleChunk,
                chunkRowIndex,
                chunkColIndex,
                chunkCellRowIndex,
                chunkCellColIndex,
                newValue);
        //} else {
        //    return get_NoDataValue();
        //}
    }

    /**
     * @param chunk
     * @param chunkRowIndex
     * @param chunkCellColIndex
     * @param chunkCellRowIndex
     * @param chunkColIndex
     * @param newValue
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     * @return Value at _CellRowIndex, _CellColIndex and sets it to newValue.
     */
    public double setCell(
            Grids_AbstractGrid2DSquareCellDoubleChunk chunk,
            int chunkRowIndex,
            int chunkColIndex,
            int chunkCellRowIndex,
            int chunkCellColIndex,
            double newValue,
            boolean handleOutOfMemoryError) {
        try {
            double result = setCell(
                    chunk,
                    chunkRowIndex,
                    chunkColIndex,
                    chunkCellRowIndex,
                    chunkCellColIndex,
                    newValue);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        chunkRowIndex,
                        chunkColIndex);
                freeSomeMemoryAndResetReserve(chunkID, e);
                return setCell(
                        chunk,
                        chunkRowIndex,
                        chunkColIndex,
                        chunkCellRowIndex,
                        chunkCellColIndex,
                        newValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param chunk
     * @param chunkRowIndex
     * @param chunkCellColIndex
     * @param chunkCellRowIndex
     * @param chunkColIndex
     * @param newValue
     * @return Value at _CellRowIndex, _CellColIndex and sets it to newValue.
     */
    protected double setCell(
            Grids_AbstractGrid2DSquareCellDoubleChunk chunk,
            int chunkRowIndex,
            int chunkColIndex,
            int chunkCellRowIndex,
            int chunkCellColIndex,
            double newValue) {
        double result = get_NoDataValue(env.HandleOutOfMemoryErrorFalse);
        if (chunk.getClass() == Grids_Grid2DSquareCellDoubleChunk64CellMap.class) {
            result = ((Grids_Grid2DSquareCellDoubleChunk64CellMap) chunk).setCell(
                    chunkCellRowIndex,
                    chunkCellColIndex,
                    newValue,
                    result);
        } else {
            if (chunk.getClass() == Grids_Grid2DSquareCellDoubleChunkArray.class) {
                result = ((Grids_Grid2DSquareCellDoubleChunkArray) chunk).setCell(
                        chunkCellRowIndex,
                        chunkCellColIndex,
                        newValue,
                        result);
            } else {
                if (chunk.getClass() == Grids_Grid2DSquareCellDoubleChunkJAI.class) {
                    result = ((Grids_Grid2DSquareCellDoubleChunkJAI) chunk).setCell(
                            chunkCellRowIndex,
                            chunkCellColIndex,
                            newValue,
                            result);
                } else {
                    if (chunk.getClass() == Grids_Grid2DSquareCellDoubleChunkMap.class) {
                        result = ((Grids_Grid2DSquareCellDoubleChunkMap) chunk).setCell(
                                chunkCellRowIndex,
                                chunkCellColIndex,
                                newValue,
                                result);
                    } else {
                        System.err.println(
                                "Error in "
                                + getClass().getName() + ".setCell(Grid2DSquareCellDoubleChunkAbstract,int,int,int,int,double) \n"
                                + "unable to handle Grid2DSquareCellChunkAbstract " + chunk.toString());
                        return result;
                    }
                }
            }
        }
        // Update Statistics
        upDateGridStatistics(
                newValue,
                result,
                _NoDataValue);
        return result;
    }

    /**
     * Initilises the value at _CellRowIndex, _CellColIndex
     *
     * @param cellRowIndex
     * @param cellColIndex
     * @param valueToInitialise
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    protected void initCell(
            long cellRowIndex,
            long cellColIndex,
            double valueToInitialise,
            boolean handleOutOfMemoryError) {
        try {
            initCell(
                    cellRowIndex,
                    cellColIndex,
                    valueToInitialise);
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                initCell(
                        cellRowIndex,
                        cellColIndex,
                        valueToInitialise,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * Initialises the value at cellRowIndex, cellColIndex.
     *
     * @param cellRowIndex
     * @param cellColIndex
     * @param valueToInitialise
     */
    protected void initCell(
            long cellRowIndex,
            long cellColIndex,
            double valueToInitialise) {
        boolean isInGrid = isInGrid(
                cellRowIndex,
                cellColIndex);
        if (isInGrid) {
            int chunkRowIndex = getChunkRowIndex(cellRowIndex);
            int chunkColIndex = getChunkColIndex(cellColIndex);
            Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                    chunkRowIndex,
                    chunkColIndex);
            //boolean containsKey = this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.containsKey( a_ChunkID ); // For debugging
            Grids_AbstractGrid2DSquareCellDoubleChunk chunk
                    = getGrid2DSquareCellDoubleChunk(chunkID);
            chunk.initCell(
                    (int) (cellRowIndex - ((long) chunkRowIndex * (long) _ChunkNRows)),
                    (int) (cellColIndex - ((long) chunkColIndex * (long) _ChunkNCols)),
                    valueToInitialise);
            // Update Statistics
            if (valueToInitialise != _NoDataValue) {
                try {
                    BigDecimal cellBigDecimal = new BigDecimal(valueToInitialise);
                    this._GridStatistics.nonNoDataValueCountBigInteger
                            = this._GridStatistics.nonNoDataValueCountBigInteger.add(BigInteger.ONE);
                    this._GridStatistics.sumBigDecimal = this._GridStatistics.sumBigDecimal.add(cellBigDecimal);
                    if (cellBigDecimal.compareTo(this._GridStatistics.minBigDecimal) == -1) {
                        this._GridStatistics.minCountBigInteger = BigInteger.ONE;
                        this._GridStatistics.minBigDecimal = cellBigDecimal;
                    } else {
                        if (cellBigDecimal.compareTo(this._GridStatistics.minBigDecimal) == 0) {
                            this._GridStatistics.minCountBigInteger
                                    = this._GridStatistics.minCountBigInteger.add(BigInteger.ONE);
                        }
                    }
                    if (cellBigDecimal.compareTo(this._GridStatistics.maxBigDecimal) == 1) {
                        this._GridStatistics.maxCountBigInteger = BigInteger.ONE;
                        this._GridStatistics.maxBigDecimal = cellBigDecimal;
                    } else {
                        if (cellBigDecimal.compareTo(this._GridStatistics.maxBigDecimal) == 0) {
                            this._GridStatistics.maxCountBigInteger
                                    = this._GridStatistics.maxCountBigInteger.add(BigInteger.ONE);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println(e.getMessage() + " in Grid2DSquareCellDouble.initCell(" + cellRowIndex + ", " + cellColIndex + ", " + valueToInitialise + ");");
                }
            }
        }
    }

    /**
     * Initialises the value at cellRowIndex, cellColIndex and does nothing
     * about this._GridStatistics
     *
     * @param cellRowIndex
     * @param cellColIndex
     * @param valueToInitialise
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    protected void initCellFast(
            long cellRowIndex,
            long cellColIndex,
            double valueToInitialise,
            boolean handleOutOfMemoryError) {
        try {
            initCellFast(
                    cellRowIndex,
                    cellColIndex,
                    valueToInitialise);
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                initCellFast(
                        cellRowIndex,
                        cellColIndex,
                        valueToInitialise,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * Initilises the value at _CellRowIndex, _CellColIndex and does nothing
     * about this._GridStatistics
     *
     * @param cellRowIndex
     * @param cellColIndex
     * @param valueToInitialise
     */
    protected void initCellFast(
            long cellRowIndex,
            long cellColIndex,
            double valueToInitialise) {
        boolean isInGrid = isInGrid(
                cellRowIndex,
                cellColIndex);
        if (isInGrid) {
            int chunkRowIndex = getChunkRowIndex(cellRowIndex);
            int chunkColIndex = getChunkColIndex(cellColIndex);
            int chunkNRows = this._ChunkNRows;
            int chunkNCols = this._ChunkNCols;
            Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                    chunkRowIndex,
                    chunkColIndex);
            Grids_AbstractGrid2DSquareCellDoubleChunk grid2DSquareCellDoubleChunk
                    = getGrid2DSquareCellDoubleChunk(chunkID);
            grid2DSquareCellDoubleChunk.initCell(
                    (int) (cellRowIndex - ((long) chunkRowIndex * (long) chunkNRows)),
                    (int) (cellColIndex - ((long) chunkColIndex * (long) chunkNCols)),
                    valueToInitialise);
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
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown. TODO
     */
    public double[] getCells(
            double x,
            double y,
            double distance,
            boolean handleOutOfMemoryError) {
        try {
            double[] result = getCells(
                    x,
                    y,
                    distance);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                long cellRowIndex = getCellRowIndex(y);
                long cellColIndex = getCellColIndex(x);
                HashSet<Grids_2D_ID_int> chunkIDs = getChunkIDs(
                        distance,
                        x,
                        y,
                        cellRowIndex,
                        cellColIndex);
                freeSomeMemoryAndResetReserve(chunkIDs, e);
                return getCells(
                        x,
                        y,
                        distance,
                        handleOutOfMemoryError);
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
                getCellRowIndex(y),
                getCellColIndex(x),
                distance);
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at centroid of cell given by cell row
     * index cellRowIndex, cell column index cellColIndex, and radius distance.
     * @param cellRowIndex the row index for the cell that's centroid is the
     * circle centre from which cell values are returned.
     * @param cellColIndex the column index for the cell that's centroid is the
     * circle centre from which cell values are returned.
     * @param distance the radius of the circle for which intersected cell
     * values are returned.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double[] getCells(
            long cellRowIndex,
            long cellColIndex,
            double distance,
            boolean handleOutOfMemoryError) {
        try {
            double[] result = getCells(
                    cellRowIndex,
                    cellColIndex,
                    distance);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                double x = getCellXDouble(cellColIndex);
                double y = getCellYDouble(cellRowIndex);
                HashSet chunkIDs = getChunkIDs(
                        distance,
                        x,
                        y,
                        cellRowIndex,
                        cellColIndex);
                freeSomeMemoryAndResetReserve(chunkIDs, a_OutOfMemoryError);
                return getCells(
                        cellRowIndex,
                        cellColIndex,
                        distance,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * @return double[] of all cell values for cells thats centroids are
     * intersected by circle with centre at centroid of cell given by cell row
     * index cellRowIndex, cell column index cellColIndex, and radius distance.
     * @param cellRowIndex the row index for the cell that's centroid is the
     * circle centre from which cell values are returned.
     * @param cellColIndex the column index for the cell that's centroid is the
     * circle centre from which cell values are returned.
     * @param distance the radius of the circle for which intersected cell
     * values are returned.
     */
    protected double[] getCells(
            long cellRowIndex,
            long cellColIndex,
            double distance) {
        return getCells(
                getCellXDouble(cellColIndex),
                getCellYDouble(cellRowIndex),
                cellRowIndex,
                cellColIndex,
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
     * @param cellRowIndex The row index at y.
     * @param cellColIndex The column index at x.
     * @param distance The radius of the circle for which intersected cell
     * values are returned.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown. TODO
     */
    public double[] getCells(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double distance,
            boolean handleOutOfMemoryError) {
        try {
            double[] result = getCells(
                    x,
                    y,
                    cellRowIndex,
                    cellColIndex,
                    distance);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                HashSet chunkIDs = getChunkIDs(
                        distance,
                        x,
                        y,
                        cellRowIndex,
                        cellColIndex);
                freeSomeMemoryAndResetReserve(chunkIDs, a_OutOfMemoryError);
                return getCells(
                        x,
                        y,
                        cellRowIndex,
                        cellColIndex,
                        distance,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
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
     * @param cellRowIndex The row index at y.
     * @param cellColIndex The column index at x.
     * @param distance The radius of the circle for which intersected cell
     * values are returned.
     */
    protected double[] getCells(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double distance) {
        double[] cells;
        int cellDistance = (int) Math.ceil(distance / _Dimensions[0].doubleValue());
        cells = new double[((2 * cellDistance) + 1) * ((2 * cellDistance) + 1)];
        long row = Long.MIN_VALUE;
        long col = Long.MIN_VALUE;
        double thisX = Double.MIN_VALUE;
        double thisY = Double.MIN_VALUE;
        int count = 0;
        for (row = cellRowIndex - cellDistance; row <= cellRowIndex + cellDistance; row++) {
            thisY = getCellYDouble(cellRowIndex);
            for (col = cellColIndex - cellDistance; col <= cellColIndex + cellDistance; col++) {
                thisX = getCellXDouble(cellColIndex);
                if (Grids_Utilities.distance(x, y, thisX, thisY) <= distance) {
                    cells[count] = getCell(
                            row,
                            col);
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
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getNearestValueDouble(
            double x,
            double y,
            boolean handleOutOfMemoryError) {
        try {
            double result = getNearestValueDouble(
                    x,
                    y);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(y),
                        getChunkColIndex(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValueDouble(
                        x,
                        y,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return the average of the nearest data values to point given by
     * x-coordinate x, y-coordinate y as a double.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    protected double getNearestValueDouble(
            double x,
            double y) {
        double result = getCell(
                x,
                y);
        if (result == _NoDataValue) {
            result = getNearestValueDouble(
                    x,
                    y,
                    getCellRowIndex(y),
                    getCellColIndex(x),
                    _NoDataValue);
        }
        return result;
    }

    /**
     * @return the average of the nearest data values to position given by row
     * index rowIndex, column index colIndex.
     * @param cellRowIndex The row index from which average of the nearest data
     * values is returned.
     * @param cellColIndex The column index from which average of the nearest
     * data values is returned.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getNearestValueDouble(
            long cellRowIndex,
            long cellColIndex,
            boolean handleOutOfMemoryError) {
        try {
            double result = getNearestValueDouble(
                    cellRowIndex,
                    cellColIndex);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValueDouble(
                        cellRowIndex,
                        cellColIndex,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param cellRowIndex The row index from which average of the nearest data
     * values is returned.
     * @param cellColIndex The column index from which average of the nearest
     * data values is returned.
     * @return the average of the nearest data values to position given by row
     * index rowIndex, column index colIndex
     */
    protected double getNearestValueDouble(
            long cellRowIndex,
            long cellColIndex) {
        double result = getCell(
                cellRowIndex,
                cellColIndex);
        if (result == _NoDataValue) {
            result = getNearestValueDouble(
                    getCellXDouble(cellColIndex),
                    getCellYDouble(cellRowIndex),
                    cellRowIndex,
                    cellColIndex,
                    _NoDataValue);
        }
        return result;
    }

    /**
     * @param noDataValue
     * @return the average of the nearest data values to point given by
     * x-coordinate x, y-coordinate y in position given by row index rowIndex,
     * column index colIndex
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param cellRowIndex the row index from which average of the nearest data
     * values is returned
     * @param cellColIndex the column index from which average of the nearest
     * data values is returned
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getNearestValueDouble(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double noDataValue,
            boolean handleOutOfMemoryError) {
        try {
            return getNearestValueDouble(
                    x,
                    y,
                    cellRowIndex,
                    cellColIndex,
                    noDataValue);
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValueDouble(
                        x,
                        y,
                        cellRowIndex,
                        cellColIndex,
                        noDataValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param noDataValue
     * @return the average of the nearest data values to point given by
     * x-coordinate x, y-coordinate y in position given by row index rowIndex,
     * column index colIndex
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param cellRowIndex the row index from which average of the nearest data
     * values is returned
     * @param cellColIndex the column index from which average of the nearest
     * data values is returned
     */
    protected double getNearestValueDouble(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double noDataValue) {
        Grids_2D_ID_long nearestCellID = getNearestCellID(
                x,
                y,
                cellRowIndex,
                cellColIndex);
        double nearestValue = getCell(
                cellRowIndex,
                cellColIndex);
        if (nearestValue == noDataValue) {
            // Find a value Seeking outwards from nearestCellID
            // Initialise visitedSet1
            HashSet visitedSet = new HashSet();
            HashSet visitedSet1 = new HashSet();
            visitedSet.add(nearestCellID);
            visitedSet1.add(nearestCellID);
            // Initialise toVisitSet1
            HashSet toVisitSet1 = new HashSet();
            long row = Long.MIN_VALUE;
            long col = Long.MIN_VALUE;
            Grids_2D_ID_long cellID0 = new Grids_2D_ID_long();
            boolean isInGrid = false;
            for (row = -1; row < 2; row++) {
                for (col = -1; col < 2; col++) {
                    if (!(row == 0 && col == 0)) {
                        isInGrid = isInGrid(
                                cellRowIndex + row,
                                cellColIndex + col);
                        if (isInGrid) {
                            cellID0 = new Grids_2D_ID_long(
                                    cellRowIndex + row,
                                    cellColIndex + col);
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
                    value = getCell(cellID0, env.HandleOutOfMemoryErrorTrue);
                    if (value != noDataValue) {
                        foundValue = true;
                        values.add(cellID0);
                    } else {
                        // Add neighbours to toVisitSet2
                        for (row = -1; row < 2; row++) {
                            for (col = -1; col < 2; col++) {
                                if (!(row == 0 && col == 0)) {
                                    isInGrid = isInGrid(
                                            cellID0.getRow() + row,
                                            cellID0.getCol() + col);
                                    if (isInGrid) {
                                        cellID1 = new Grids_2D_ID_long(
                                                cellID0.getRow() + row,
                                                cellID0.getCol() + col);
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
                    if (getCell(cellID, env.HandleOutOfMemoryErrorTrue) != noDataValue) {
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
                value += getCell(cellID0, env.HandleOutOfMemoryErrorTrue);
            }
            nearestValue = value / (double) closest.size();
        }
        return nearestValue;
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data values to
     * point given by x-coordinate x, y-coordinate y.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public Grids_2D_ID_long[] getNearestValuesCellIDs(
            double x,
            double y,
            boolean handleOutOfMemoryError) {
        try {
            Grids_2D_ID_long[] result = getNearestValuesCellIDs(
                    x,
                    y);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(y),
                        getChunkColIndex(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValuesCellIDs(
                        x,
                        y,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data values to
     * point given by x-coordinate x, y-coordinate y.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    protected Grids_2D_ID_long[] getNearestValuesCellIDs(
            double x,
            double y) {
        double value = getCell(
                x,
                y);
        if (value == _NoDataValue) {
            return getNearestValuesCellIDs(
                    x,
                    y,
                    getCellRowIndex(y),
                    getCellColIndex(x),
                    _NoDataValue);
        }
        Grids_2D_ID_long[] cellIDs = new Grids_2D_ID_long[1];
        cellIDs[0] = getCellID(
                x,
                y);
        return cellIDs;
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data values to
     * position given by row index rowIndex, column index colIndex.
     * @param cellRowIndex The row index from which the cell IDs of the nearest
     * cells with data values are returned.
     * @param cellColIndex The column index from which the cell IDs of the
     * nearest cells with data values are returned.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public Grids_2D_ID_long[] getNearestValuesCellIDs(
            long cellRowIndex,
            long cellColIndex,
            boolean handleOutOfMemoryError) {
        try {
            Grids_2D_ID_long[] result = getNearestValuesCellIDs(
                    cellRowIndex,
                    cellColIndex);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValuesCellIDs(
                        cellRowIndex,
                        cellColIndex,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data values to
     * position given by row index rowIndex, column index colIndex.
     * @param cellRowIndex The row index from which the cell IDs of the nearest
     * cells with data values are returned.
     * @param cellColIndex The column index from which the cell IDs of the
     * nearest cells with data values are returned.
     */
    protected Grids_2D_ID_long[] getNearestValuesCellIDs(
            long cellRowIndex,
            long cellColIndex) {
        double value = getCell(
                cellRowIndex,
                cellColIndex);
        if (value == _NoDataValue) {
            return getNearestValuesCellIDs(
                    getCellXDouble(cellColIndex),
                    getCellYDouble(cellRowIndex),
                    cellRowIndex,
                    cellColIndex,
                    _NoDataValue);
        }
        Grids_2D_ID_long[] cellIDs = new Grids_2D_ID_long[1];
        cellIDs[0] = getCellID(
                cellRowIndex,
                cellColIndex);
        return cellIDs;
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data values
     * nearest to point with position given by: x-coordinate x, y-coordinate y;
     * and, cell row index _CellRowIndex, cell column index _CellColIndex.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param cellRowIndex The row index from which the cell IDs of the nearest
     * cells with data values are returned.
     * @param cellColIndex The column index from which the cell IDs of the
     * nearest cells with data values are returned.
     * @param _NoDataValue the no data value of the grid
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public Grids_2D_ID_long[] getNearestValuesCellIDs(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double _NoDataValue,
            boolean handleOutOfMemoryError) {
        try {
            Grids_2D_ID_long[] result = getNearestValuesCellIDs(
                    x,
                    y,
                    cellRowIndex,
                    cellColIndex,
                    _NoDataValue);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValuesCellIDs(
                        x,
                        y,
                        cellRowIndex,
                        cellColIndex,
                        _NoDataValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return a Grids_2D_ID_long[] - The CellIDs of the nearest cells with data values
     * nearest to point with position given by: x-coordinate x, y-coordinate y;
     * and, cell row index _CellRowIndex, cell column index _CellColIndex.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param cellRowIndex The row index from which the cell IDs of the nearest
     * cells with data values are returned.
     * @param cellColIndex The column index from which the cell IDs of the
     * nearest cells with data values are returned.
     * @param _NoDataValue the no data value of the grid
     */
    protected Grids_2D_ID_long[] getNearestValuesCellIDs(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double _NoDataValue) {
        Grids_2D_ID_long[] nearestCellIDs = new Grids_2D_ID_long[1];
        nearestCellIDs[0] = getNearestCellID(
                x,
                y,
                cellRowIndex,
                cellColIndex);
        double nearestCellValue = getCell(
                cellRowIndex,
                cellColIndex);
        if (nearestCellValue == _NoDataValue) {
            // Find a value Seeking outwards from nearestCellID
            // Initialise visitedSet1
            HashSet visitedSet = new HashSet();
            HashSet visitedSet1 = new HashSet();
            visitedSet.add(nearestCellIDs[0]);
            visitedSet1.add(nearestCellIDs[0]);
            // Initialise toVisitSet1
            HashSet toVisitSet1 = new HashSet();
            long row;
            long col;
            boolean isInGrid;
            Grids_2D_ID_long cellID;
            for (row = -1; row < 2; row++) {
                for (col = -1; col < 2; col++) {
                    if (!(row == 0 && col == 0)) {
                        isInGrid = isInGrid(
                                cellRowIndex + row,
                                cellColIndex + col);
                        if (isInGrid) {
                            cellID = getCellID(
                                    cellRowIndex + row,
                                    cellColIndex + col);
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
                    value = getCell(
                            cellID,
                            env.HandleOutOfMemoryErrorTrue);
                    if (value != _NoDataValue) {
                        foundValue = true;
                        values.add(cellID);
                    } else {
                        // Add neighbours to toVisitSet2
                        for (row = -1; row < 2; row++) {
                            for (col = -1; col < 2; col++) {
                                if (!(row == 0 && col == 0)) {
                                    isInGrid = isInGrid(
                                            cellID.getRow() + row,
                                            cellID.getCol() + col);
                                    if (isInGrid) {
                                        cellID = getCellID(
                                                cellID.getRow() + row,
                                                cellID.getCol() + col);
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
                    if (getCell(cellID1, env.HandleOutOfMemoryErrorTrue) != _NoDataValue) {
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
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getNearestValueDoubleDistance(
            double x,
            double y,
            boolean handleOutOfMemoryError) {
        try {
            double result = getNearestValueDoubleDistance(
                    x,
                    y);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(y),
                        getChunkColIndex(x));
                freeSomeMemoryAndResetReserve(chunkID, a_OutOfMemoryError);
                return getNearestValueDoubleDistance(
                        x,
                        y,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     * @return the distance to the nearest data value from point given by
     * x-coordinate x, y-coordinate y as a double.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    protected double getNearestValueDoubleDistance(
            double x,
            double y) {
        double result = getCell(
                x,
                y);
        if (result == _NoDataValue) {
            result = getNearestValueDoubleDistance(
                    x,
                    y,
                    getCellRowIndex(y),
                    getCellColIndex(x),
                    _NoDataValue);
        }
        return result;
    }

    /**
     * @return the distance to the nearest data value from position given by row
     * index rowIndex, column index colIndex as a double.
     * @param cellRowIndex The cell row index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     * @param cellColIndex The cell column index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getNearestValueDoubleDistance(
            long cellRowIndex,
            long cellColIndex,
            boolean handleOutOfMemoryError) {
        try {
            double result = getNearestValueDoubleDistance(
                    cellRowIndex,
                    cellColIndex);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValueDoubleDistance(
                        cellRowIndex,
                        cellColIndex,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return the distance to the nearest data value from position given by row
     * index rowIndex, column index colIndex as a double.
     * @param cellRowIndex The cell row index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     * @param cellColIndex The cell column index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     */
    protected double getNearestValueDoubleDistance(
            long cellRowIndex,
            long cellColIndex) {
        double result = getCell(
                cellRowIndex,
                cellColIndex);
        if (result == _NoDataValue) {
            result = getNearestValueDoubleDistance(
                    getCellXDouble(cellColIndex),
                    getCellYDouble(cellRowIndex),
                    cellRowIndex,
                    cellColIndex,
                    _NoDataValue);
        }
        return result;
    }

    /**
     * @param noDataValue
     * @return the distance to the nearest data value from: point given by
     * x-coordinate x, y-coordinate y in position given by row index rowIndex,
     * column index colIndex as a double.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param cellRowIndex The cell row index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     * @param cellColIndex The cell column index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double getNearestValueDoubleDistance(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double noDataValue,
            boolean handleOutOfMemoryError) {
        try {
            double result = getNearestValueDoubleDistance(
                    x,
                    y,
                    cellRowIndex,
                    cellColIndex,
                    noDataValue);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return getNearestValueDoubleDistance(
                        x,
                        y,
                        cellRowIndex,
                        cellColIndex,
                        noDataValue,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @param noDataValue
     * @return the distance to the nearest data value from: point given by
     * x-coordinate x, y-coordinate y in position given by row index rowIndex,
     * column index colIndex as a double.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     * @param cellRowIndex The cell row index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     * @param cellColIndex The cell column index of the cell from which the
     * distance nearest to the nearest cell value is returned.
     */
    protected double getNearestValueDoubleDistance(
            double x,
            double y,
            long cellRowIndex,
            long cellColIndex,
            double noDataValue) {
        double result = getCell(
                cellRowIndex,
                cellColIndex);
        if (result == noDataValue) {
            // Initialisation
            long long0 = Long.MIN_VALUE;
            long long1 = Long.MIN_VALUE;
            long longMinus1 = -1;
            long longTwo = 2;
            long longZero = 0;
            boolean boolean0 = false;
            boolean boolean1 = false;
            boolean boolean2 = false;
            double double0 = Double.NEGATIVE_INFINITY;
            double double1 = Double.NEGATIVE_INFINITY;
            Grids_2D_ID_long nearestCellID = getNearestCellID(
                    x,
                    y,
                    cellRowIndex,
                    cellColIndex);
            HashSet visitedSet = new HashSet();
            HashSet visitedSet1 = new HashSet();
            visitedSet.add(nearestCellID);
            visitedSet1.add(nearestCellID);
            HashSet toVisitSet1 = new HashSet();
            long row = Long.MIN_VALUE;
            long col = Long.MIN_VALUE;
            boolean isInGrid = false;
            Grids_2D_ID_long cellID = new Grids_2D_ID_long();
            boolean foundValue = false;
            double value = Double.NEGATIVE_INFINITY;
            HashSet values = new HashSet();
            HashSet visitedSet2 = new HashSet();
            HashSet toVisitSet2 = new HashSet();
            Iterator iterator = toVisitSet1.iterator();
            double distance = Double.NEGATIVE_INFINITY;
            double minDistance = Double.MAX_VALUE;
            HashSet closest = new HashSet();
            // Find a value Seeking outwards from nearestCellID
            // Initialise toVisitSet1
            for (row = longMinus1; row < longTwo; row++) {
                for (col = longMinus1; col < longTwo; col++) {
                    boolean0 = (row == longZero);
                    boolean1 = (col == longZero);
                    boolean2 = !(boolean0 && boolean1);
                    if (boolean2) {
                        long0 = cellRowIndex + row;
                        long1 = cellColIndex + col;
                        isInGrid = isInGrid(
                                long0,
                                long1,
                                env.HandleOutOfMemoryErrorTrue);
                        if (isInGrid) {
                            cellID = getCellID(
                                    long0,
                                    long1,
                                    env.HandleOutOfMemoryErrorTrue);
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
                    value = getCell(
                            cellID,
                            env.HandleOutOfMemoryErrorTrue);
                    if (value != noDataValue) {
                        foundValue = true;
                        values.add(cellID);
                    } else {
                        // Add neighbours to toVisitSet2
                        for (row = longMinus1; row < longTwo; row++) {
                            for (col = longMinus1; col < longTwo; col++) {
                                boolean0 = (row == longZero);
                                boolean1 = (col == longZero);
                                boolean2 = !(boolean0 && boolean1);
                                if (boolean2) {
                                    long0 = cellID.getRow() + row;
                                    long1 = cellID.getCol() + col;
                                    isInGrid = isInGrid(
                                            long0,
                                            long1,
                                            env.HandleOutOfMemoryErrorTrue);
                                    if (isInGrid) {
                                        cellID = getCellID(
                                                long0,
                                                long1,
                                                env.HandleOutOfMemoryErrorTrue);
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
                double0 = getCellXDouble(
                        cellID,
                        env.HandleOutOfMemoryErrorTrue);
                double1 = getCellYDouble(
                        cellID,
                        env.HandleOutOfMemoryErrorTrue);
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
                    if (getCell(cellID1, env.HandleOutOfMemoryErrorTrue) != noDataValue) {
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
     * @return current value of cell containing the point given by x-coordinate
     * x, y-coordinate y, and adds valueToAdd to that cell.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param valueToAdd the value to be added to the cell containing the point
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double addToCell(
            double x,
            double y,
            double valueToAdd,
            boolean handleOutOfMemoryError) {
        try {
            double result = addToCell(
                    x,
                    y,
                    valueToAdd);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(y),
                        getChunkColIndex(x));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return addToCell(
                        x,
                        y,
                        valueToAdd,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return current value of cell containing the point given by x-coordinate
     * x, y-coordinate y, and adds valueToAdd to that cell.
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param valueToAdd the value to be added to the cell containing the point
     */
    protected double addToCell(
            double x,
            double y,
            double valueToAdd) {
        return addToCell(
                getCellRowIndex(y),
                getCellColIndex(x),
                valueToAdd);
    }

    /**
     * @return Value of the cell with cell Grids_2D_ID_int cellID and adds valueToAdd to that
     * cell.
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param valueToAdd the value to be added to the cell containing the point
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    public double addToCell(
            Grids_2D_ID_long cellID,
            double valueToAdd,
            boolean handleOutOfMemoryError) {
        try {
            double result = addToCell(
                    cellID,
                    valueToAdd);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellID.getRow()),
                        getChunkColIndex(cellID.getCol()));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return addToCell(
                        cellID,
                        valueToAdd,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return Value of the cell with cell Grids_2D_ID_int cellID and adds valueToAdd to that
     * cell.
     * @param cellID the Grids_2D_ID_long of the cell.
     * @param valueToAdd the value to be added to the cell containing the point
     */
    protected double addToCell(
            Grids_2D_ID_long cellID,
            double valueToAdd) {
        return addToCell(
                cellID.getRow(),
                cellID.getCol(),
                valueToAdd);
    }

    /**
     * @return current value of cell with row index rowIndex and column index
     * colIndex and adds valueToAdd to that cell.
     * @param cellRowIndex the row index of the cell.
     * @param cellColIndex the column index of the cell.
     * @param valueToAdd the value to be added to the cell.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown. NB1. If cell is not
     * contained in this then then returns _NoDataValue. NB2. Adding to
     * _NoDataValue is done as if adding to a cell with value of 0. TODO: Check
     * Arithmetic
     */
    public double addToCell(
            long cellRowIndex,
            long cellColIndex,
            double valueToAdd,
            boolean handleOutOfMemoryError) {
        try {
            double result = addToCell(
                    cellRowIndex,
                    cellColIndex,
                    valueToAdd);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                Grids_2D_ID_int chunkID = new Grids_2D_ID_int(
                        getChunkRowIndex(cellRowIndex),
                        getChunkColIndex(cellColIndex));
                freeSomeMemoryAndResetReserve(chunkID, e);
                return addToCell(
                        cellRowIndex,
                        cellColIndex,
                        valueToAdd,
                        handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }

    /**
     * @return current value of cell with row index rowIndex and column index
     * colIndex and adds valueToAdd to that cell.
     * @param cellRowIndex the row index of the cell.
     * @param cellColIndex the column index of the cell.
     * @param valueToAdd the value to be added to the cell. NB1. If cell is not
     * contained in this then then returns _NoDataValue. NB2. Adding to
     * _NoDataValue is done as if adding to a cell with value of 0. TODO: Check
     * Arithmetic
     */
    protected double addToCell(
            long cellRowIndex,
            long cellColIndex,
            double valueToAdd) {
        boolean isInGrid = isInGrid(
                cellRowIndex,
                cellColIndex);
        if (isInGrid) {
            double currentValue = getCell(
                    cellRowIndex,
                    cellColIndex);
            if (currentValue != _NoDataValue) {
                if (valueToAdd != _NoDataValue) {
                    return setCell(
                            cellRowIndex,
                            cellColIndex,
                            currentValue + valueToAdd);
                }
            } else {
                if (valueToAdd != _NoDataValue) {
                    return setCell(
                            cellRowIndex,
                            cellColIndex,
                            valueToAdd);
                }
            }
        }
        return _NoDataValue;
    }

    /**
     *
     * @param value
     * @param handleOutOfMemoryError
     */
    public void init_Cells(
            double value,
            boolean handleOutOfMemoryError) {
        try {
            init_Cells(value);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
        } catch (OutOfMemoryError a_OutOfMemoryError) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                if (env.swapToFile_Grid2DSquareCellChunk_Account() < 1L) {
                    throw a_OutOfMemoryError;
                }
                env.init_MemoryReserve(handleOutOfMemoryError);
                init_Cells(
                        value,
                        handleOutOfMemoryError);
            } else {
                throw a_OutOfMemoryError;
            }
        }
    }

    /**
     *
     * @param value
     */
    protected void init_Cells(double value) {
        Iterator<Grids_2D_ID_int> a_Iterator = this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.keySet().iterator();
        int nChunks = this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.size();
        Grids_AbstractGrid2DSquareCellDoubleChunk a_Grid2DSquareCellDoubleChunk;
        int chunkNRows = 0;
        int chunkNCols = 0;
        int row = 0;
        int col = 0;
        Grids_2D_ID_int chunkID = new Grids_2D_ID_int();
        int counter = 0;
        boolean handleOutOfMemoryError = true;
        while (a_Iterator.hasNext()) {
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            System.out.println(
                    "Initialising Chunk " + counter
                    + " out of " + nChunks);
            counter++;
            chunkID = a_Iterator.next();
            a_Grid2DSquareCellDoubleChunk = (Grids_AbstractGrid2DSquareCellDoubleChunk) this._ChunkID_AbstractGrid2DSquareCellChunk_HashMap.get(chunkID);
            chunkNRows = getChunkNRows(chunkID, handleOutOfMemoryError);
            chunkNCols = getChunkNCols(chunkID, handleOutOfMemoryError);
            for (row = 0; row <= chunkNRows; row++) {
                for (col = 0; col <= chunkNCols; col++) {
                    a_Grid2DSquareCellDoubleChunk.setCell(
                            chunkNRows,
                            chunkNCols,
                            col,
                            col,
                            handleOutOfMemoryError);
                }
            }
        }
    }

    /**
     * @return A Grids_Grid2DSquareCellDoubleIterator for iterating over the
     * cell values in this.
     * @param handleOutOfMemoryError If true then OutOfMemoryErrors are caught,
     * swap operations are initiated, then the method is re-called. If false
     * then OutOfMemoryErrors are caught and thrown.
     */
    @Override
    public Iterator<Double> iterator(
            boolean handleOutOfMemoryError) {
        try {
            Iterator result = new Grids_Grid2DSquareCellDoubleIterator(this);
            env.tryToEnsureThereIsEnoughMemoryToContinue(handleOutOfMemoryError);
            return result;
        } catch (OutOfMemoryError e) {
            if (handleOutOfMemoryError) {
                env.clear_MemoryReserve();
                freeSomeMemoryAndResetReserve(handleOutOfMemoryError, e);
                return iterator(handleOutOfMemoryError);
            } else {
                throw e;
            }
        }
    }
}
