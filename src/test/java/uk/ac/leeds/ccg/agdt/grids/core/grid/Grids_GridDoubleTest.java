/*
 * Copyright 2019 Andy Turner, University of Leeds.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.ac.leeds.ccg.agdt.grids.core.grid;

import uk.ac.leeds.ccg.agdt.grids.core.grid.Grids_GridDoubleFactory;
import uk.ac.leeds.ccg.agdt.grids.core.grid.Grids_GridDouble;
import java.io.File;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.leeds.ccg.agdt.grids.core.Grids_2D_ID_int;
import uk.ac.leeds.ccg.agdt.grids.core.Grids_Dimensions;
import uk.ac.leeds.ccg.agdt.grids.core.Grids_Environment;
import uk.ac.leeds.ccg.agdt.grids.core.grid.chunk.Grids_GridChunkDoubleArrayFactory;
import uk.ac.leeds.ccg.agdt.grids.core.grid.stats.Grids_GridDoubleStats;
import uk.ac.leeds.ccg.agdt.grids.core.grid.stats.Grids_GridDoubleStatsNotUpdated;
import uk.ac.leeds.ccg.agdt.grids.process.Grids_Processor;

/**
 *
 *
 * @author Andy Turner
 * @version 1.0.0
 */
public class Grids_GridDoubleTest {

    public Grids_GridDoubleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    /**
//     * Test of toString method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        String expResult = "";
//        String result = instance.toString(HOOME);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getChunk method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetGridChunk_3args() {
//        System.out.println("getChunk");
//        int chunkRow = 0;
//        int chunkCol = 0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_AbstractGridChunkDouble expResult = null;
//        Grids_AbstractGridChunkDouble result = instance.getChunk(chunkRow, chunkCol, HOOME);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getChunk method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetGridChunk_int_int() {
//        System.out.println("getChunk");
//        int chunkRow = 0;
//        int chunkCol = 0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_AbstractGridChunkDouble expResult = null;
//        Grids_AbstractGridChunkDouble result = instance.getChunk(chunkRow, chunkCol);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getChunk method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetGrid2DSquareCellDoubleChunk_Grids_2D_ID_int_boolean() {
//        System.out.println("getChunk");
//        Grids_2D_ID_int chunkID = null;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_AbstractGridChunkDouble expResult = null;
//        Grids_AbstractGridChunkDouble result = instance.getChunk(chunkID, HOOME);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getChunk method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetGrid2DSquareCellDoubleChunk_Grids_2D_ID_int() {
//        System.out.println("getChunk");
//        Grids_2D_ID_int chunkID = null;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_AbstractGridChunkDouble expResult = null;
//        Grids_AbstractGridChunkDouble result = instance.getChunk(chunkID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNoDataValue method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNoDataValue() {
//        System.out.println("getNoDataValue");
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNoDataValue(HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNoDataValueBigDecimal method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNoDataValueBigDecimal() {
//        System.out.println("getNoDataValueBigDecimal");
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        BigDecimal expResult = null;
//        BigDecimal result = instance.getNoDataValueBigDecimal(HOOME);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initNoDataValue method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testInitNoDataValue() {
//        System.out.println("initNoDataValue");
//        double noDataValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        instance.initNoDataValue(noDataValue);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    File dir;

    /**
     * Initialises dir.
     */
    protected void initDir() {
        dir = new File(System.getProperty("user.dir"));
        dir = new File(dir, "Grids");
        dir = new File(dir, "Test");
        dir.mkdirs();
        System.out.println("dir " + dir);
    }

    /**
     * Gets a Grids_Factory.
     *
     * @param ge
     * @param dir
     * @param chunkNRows
     * @param chunkNCols
     * @param nRows
     * @param nCols
     * @param noDataValue
     * @param chunkFactory
     * @param handleOutOfMemoryError
     * @return
     */
    protected Grids_GridDoubleFactory getFactory(
            Grids_Environment ge,
            File dir,
            int chunkNRows,
            int chunkNCols,
            long nRows,
            long nCols,
            double noDataValue,
            Grids_GridChunkDoubleArrayFactory chunkFactory,
            boolean handleOutOfMemoryError) {
        Grids_Processor gp;
        gp = ge.getProcessor();
        Grids_GridDoubleFactory result;
        result = new Grids_GridDoubleFactory(ge,
                gp.GridChunkDoubleFactory,
                //gp.DefaultGridChunkDoubleFactory,
                chunkFactory, noDataValue, chunkNRows, chunkNCols,
                new Grids_Dimensions(nRows, nCols),
                new Grids_GridDoubleStatsNotUpdated(ge));
        return result;
    }

    /**
     *
     * @param nRows
     * @param nCols
     * @return
     */
    protected Grids_Dimensions getDimensions(long nRows, long nCols) {
        BigDecimal cellsize = BigDecimal.ONE;
        Grids_Dimensions result;
        BigDecimal xMin = BigDecimal.ONE;
        BigDecimal xMax = new BigDecimal(nCols).multiply(cellsize);
        BigDecimal yMin = BigDecimal.ONE;
        BigDecimal yMax = new BigDecimal(nRows).multiply(cellsize);
        result = new Grids_Dimensions(xMin, xMax, yMin, yMax, cellsize);
        return result;
    }

    /**
     * Gets a Grids_GridDouble
     *
     * @param dir
     * @param name
     * @param gridDoubleFactory
     * @param nRows
     * @param handleOutOfMemoryError
     * @param nCols
     * @return
     */
    protected Grids_GridDouble getGridDouble(
            File dir,
            String name,
            Grids_GridDoubleFactory gridDoubleFactory,
            long nRows,
            long nCols,
            boolean handleOutOfMemoryError) {
        Grids_GridDouble result;
        Grids_Dimensions dimensions;
        dimensions = getDimensions(nRows, nCols);
        Grids_GridDoubleStats gridStatistics;
        gridStatistics = new Grids_GridDoubleStats(gridDoubleFactory.env);
        result = gridDoubleFactory.create(
                gridStatistics,
                gridDoubleFactory.env.files.getGeneratedGridDoubleDir(),
                gridDoubleFactory.GridChunkDoubleFactory,
                nRows,
                nCols,
                dimensions);
        return result;
    }

    double noDataValue;
    boolean HOOME;
    Grids_GridChunkDoubleArrayFactory chunkFactory;
    Grids_Environment ge;

    /**
     * Test create Grids_GridDouble.
     */
    @Test
    public void test1() {
//        noDataValue = -9999.0d;
//        HOOME = true;
//        chunkFactory = new Grids_GridChunkDoubleArrayFactory();
//        initDir();
//        env = new Grids_Environment(dir);
//        Grids_GridDoubleFactory gridFactory;
//
//        int chunkNRows;
//        int chunkNCols;
//        long nRows;
//        long nCols;
//        String name;
//
//        chunkNRows = 10;
//        chunkNCols = 10;
//        nRows = 1000;
//        nCols = 1000;
//        name = "TestGrid2";
//
//        gridFactory = getFactory(env, dir, chunkNRows, chunkNCols, nRows, nCols, 
//                noDataValue, chunkFactory, HOOME);
//
//        Grids_GridDouble g;
//        g = getGridDouble(dir, name, gridFactory, nRows, nCols, HOOME);
//
//        int nChunkRows;
//        int nChunkCols;
//        nChunkRows = g.getNChunkRows();
//        nChunkCols = g.getNChunkCols();
//
//        int chunkRow;
//        int chunkCol;
//        int row;
//        int col;
//
//        // Set all values to 20.
//        double value;
//        value = 20d;
//
//        for (chunkRow = 0; chunkRow < nChunkRows; chunkRow++) {
//            chunkNRows = g.getChunkNRows(chunkRow);
//            System.out.println("chunkNRows " + chunkNRows);
//            for (chunkCol = 0; chunkCol < nChunkCols; chunkCol++) {
//                System.out.println("chunkCol " + chunkCol);
//                Grids_2D_ID_int chunkID;
//                chunkID = new Grids_2D_ID_int(chunkRow, chunkCol);
//                chunkNCols = g.getChunkNCols(chunkCol);
//                System.out.println("chunkNCols " + chunkNCols);
//                for (row = 0; row < chunkNRows; row++) {
//                    for (col = 0; col < chunkNCols; col++) {
//                        g.setCell(chunkRow, chunkCol, row, col, value);
//                    }
//                }
//                System.out.println("Done " + chunkID.toString());
//            }
//        }
//        double expResult;
//        double result;
//
//        row = 100;
//        col = 100;
//        expResult = value;
//        result = g.getCell(row, col);
//        assertEquals(expResult, result, value);

    }

    /**
     * Test of getCell method, of class Grids_GridDouble.
     */
    @Test
    public void testGetCell_3args_1() {
//        System.out.println("getCell");
//        noDataValue = -9999.0d;
//        HOOME = true;
//        chunkFactory = new Grids_GridChunkDoubleArrayFactory();
//        initDir();
//        env = new Grids_Environment(dir);
//        Grids_GridDoubleFactory gridFactory;
//
//        int chunkNRows;
//        int chunkNCols;
//        long nRows;
//        long nCols;
//        long cellRowIndex;
//        long cellColIndex;
//        String name;
//
//        chunkNRows = 24;
//        chunkNCols = 49;
//        nRows = 101;
//        nCols = 100;
//        name = "TestGrid1";
//
//        gridFactory = getFactory(env, dir, chunkNRows, chunkNCols, nRows, nCols,
//                noDataValue, chunkFactory, HOOME);
//
//        Grids_GridDouble g;
//        g = getGridDouble(dir, name, gridFactory, nRows, nCols, HOOME);
//
//        double value;
//        double expResult;
//        double result;
//
//        value = 20d;
//
//        cellRowIndex = 0L;
//        cellColIndex = 0L;
//        g.setCell(cellRowIndex, cellColIndex, value);
//        expResult = value;
//        result = g.getCell(cellRowIndex, cellColIndex);
//        assertEquals(expResult, result, 0.0);
//
//        cellRowIndex = nRows - 1;
//        cellColIndex = nCols - 1;
//        g.setCell(cellRowIndex, cellColIndex, value);
//        expResult = value;
//        result = g.getCell(cellRowIndex, cellColIndex);
//        assertEquals(expResult, result, 0.0);
//
//        cellRowIndex = nRows;
//        cellColIndex = nCols - 1;
//        g.setCell(cellRowIndex, cellColIndex, value);
//        expResult = noDataValue;
//        result = g.getCell(cellRowIndex, cellColIndex);
//        assertEquals(expResult, result, 0.0);
//
//        cellRowIndex = nRows - 1;
//        cellColIndex = nCols;
//        g.setCell(cellRowIndex, cellColIndex, value);
//        expResult = noDataValue;
//        result = g.getCell(cellRowIndex, cellColIndex);
//        assertEquals(expResult, result, 0.0);

    }

//    /**
//     * Test of getCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCell_long_long() {
//        System.out.println("getCell");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getCell(cellRowIndex, cellColIndex);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCell_6args() {
//        System.out.println("getCell");
//        Grids_AbstractGridChunkDouble grid2DSquareCellChunk = null;
//        int chunkRow = 0;
//        int chunkCol = 0;
//        int chunkCellRowIndex = 0;
//        int chunkCellColIndex = 0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getCell(grid2DSquareCellChunk, chunkRow, chunkCol, chunkCellRowIndex, chunkCellColIndex, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCell_5args() {
//        System.out.println("getCell");
//        Grids_AbstractGridChunkDouble grid2DSquareCellChunk = null;
//        int chunkRow = 0;
//        int chunkCol = 0;
//        int chunkCellRowIndex = 0;
//        int chunkCellColIndex = 0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getCell(grid2DSquareCellChunk, chunkRow, chunkCol, chunkCellRowIndex, chunkCellColIndex);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCell_3args_2() {
//        System.out.println("getCell");
//        double x = 0.0;
//        double y = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getCell(x, y, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCell_double_double() {
//        System.out.println("getCell");
//        double x = 0.0;
//        double y = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getCell(x, y);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCell_Grids_2D_ID_long_boolean() {
//        System.out.println("getCell");
//        Grids_2D_ID_long cellID = null;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getCell(cellID, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_4args() {
//        System.out.println("setCell");
//        double x = 0.0;
//        double y = 0.0;
//        double newValue = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(x, y, newValue, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_3args_1() {
//        System.out.println("setCell");
//        double x = 0.0;
//        double y = 0.0;
//        double newValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(x, y, newValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_3args_2() {
//        System.out.println("setCell");
//        Grids_2D_ID_long cellID = null;
//        double newValue = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(cellID, newValue, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_3args_3() {
//        System.out.println("setCell");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double newValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(cellRowIndex, cellColIndex, newValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_6args_1() {
//        System.out.println("setCell");
//        int chunkRow = 0;
//        int chunkCol = 0;
//        int chunkCellRowIndex = 0;
//        int chunkCellColIndex = 0;
//        double newValue = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(chunkRow, chunkCol, chunkCellRowIndex, chunkCellColIndex, newValue, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_5args() {
//        System.out.println("setCell");
//        int chunkRow = 0;
//        int chunkCol = 0;
//        int chunkCellRowIndex = 0;
//        int chunkCellColIndex = 0;
//        double newValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(chunkRow, chunkCol, chunkCellRowIndex, chunkCellColIndex, newValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_7args() {
//        System.out.println("setCell");
//        Grids_AbstractGridChunkDouble chunk = null;
//        int chunkRow = 0;
//        int chunkCol = 0;
//        int chunkCellRowIndex = 0;
//        int chunkCellColIndex = 0;
//        double newValue = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(chunk, chunkRow, chunkCol, chunkCellRowIndex, chunkCellColIndex, newValue, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testSetCell_6args_2() {
//        System.out.println("setCell");
//        Grids_AbstractGridChunkDouble chunk = null;
//        int chunkRow = 0;
//        int chunkCol = 0;
//        int chunkCellRowIndex = 0;
//        int chunkCellColIndex = 0;
//        double newValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.setCell(chunk, chunkRow, chunkCol, chunkCellRowIndex, chunkCellColIndex, newValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testInitCell_4args() {
//        System.out.println("initCell");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double valueToInitialise = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        instance.initCell(cellRowIndex, cellColIndex, valueToInitialise, HOOME);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testInitCell_3args() {
//        System.out.println("initCell");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double valueToInitialise = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        instance.initCell(cellRowIndex, cellColIndex, valueToInitialise);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initCellFast method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testInitCellFast_4args() {
//        System.out.println("initCellFast");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double valueToInitialise = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        instance.initCellFast(cellRowIndex, cellColIndex, valueToInitialise, HOOME);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initCellFast method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testInitCellFast_3args() {
//        System.out.println("initCellFast");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double valueToInitialise = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        instance.initCellFast(cellRowIndex, cellColIndex, valueToInitialise);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCells_4args_1() {
//        System.out.println("getCells");
//        double x = 0.0;
//        double y = 0.0;
//        double distance = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double[] expResult = null;
//        double[] result = instance.getCells(x, y, distance, HOOME);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCells_3args_1() {
//        System.out.println("getCells");
//        double x = 0.0;
//        double y = 0.0;
//        double distance = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double[] expResult = null;
//        double[] result = instance.getCells(x, y, distance);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCells_4args_2() {
//        System.out.println("getCells");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double distance = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double[] expResult = null;
//        double[] result = instance.getCells(cellRowIndex, cellColIndex, distance, HOOME);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCells_3args_2() {
//        System.out.println("getCells");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double distance = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double[] expResult = null;
//        double[] result = instance.getCells(cellRowIndex, cellColIndex, distance);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCells_6args() {
//        System.out.println("getCells");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double distance = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double[] expResult = null;
//        double[] result = instance.getCells(x, y, cellRowIndex, cellColIndex, distance, HOOME);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetCells_5args() {
//        System.out.println("getCells");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double distance = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double[] expResult = null;
//        double[] result = instance.getCells(x, y, cellRowIndex, cellColIndex, distance);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDouble method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDouble_3args_1() {
//        System.out.println("getNearestValueDouble");
//        double x = 0.0;
//        double y = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDouble(x, y, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDouble method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDouble_double_double() {
//        System.out.println("getNearestValueDouble");
//        double x = 0.0;
//        double y = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDouble(x, y);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDouble method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDouble_3args_2() {
//        System.out.println("getNearestValueDouble");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDouble(cellRowIndex, cellColIndex, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDouble method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDouble_long_long() {
//        System.out.println("getNearestValueDouble");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDouble(cellRowIndex, cellColIndex);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDouble method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDouble_6args() {
//        System.out.println("getNearestValueDouble");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double noDataValue = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDouble(x, y, cellRowIndex, cellColIndex, noDataValue, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDouble method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDouble_5args() {
//        System.out.println("getNearestValueDouble");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double noDataValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDouble(x, y, cellRowIndex, cellColIndex, noDataValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValuesCellIDs method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValuesCellIDs_3args_1() {
//        System.out.println("getNearestValuesCellIDs");
//        double x = 0.0;
//        double y = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_2D_ID_long[] expResult = null;
//        Grids_2D_ID_long[] result = instance.getNearestValuesCellIDs(x, y, HOOME);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValuesCellIDs method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValuesCellIDs_double_double() {
//        System.out.println("getNearestValuesCellIDs");
//        double x = 0.0;
//        double y = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_2D_ID_long[] expResult = null;
//        Grids_2D_ID_long[] result = instance.getNearestValuesCellIDs(x, y);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValuesCellIDs method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValuesCellIDs_3args_2() {
//        System.out.println("getNearestValuesCellIDs");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_2D_ID_long[] expResult = null;
//        Grids_2D_ID_long[] result = instance.getNearestValuesCellIDs(cellRowIndex, cellColIndex, HOOME);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValuesCellIDs method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValuesCellIDs_long_long() {
//        System.out.println("getNearestValuesCellIDs");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_2D_ID_long[] expResult = null;
//        Grids_2D_ID_long[] result = instance.getNearestValuesCellIDs(cellRowIndex, cellColIndex);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValuesCellIDs method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValuesCellIDs_6args() {
//        System.out.println("getNearestValuesCellIDs");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double _NoDataValue = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_2D_ID_long[] expResult = null;
//        Grids_2D_ID_long[] result = instance.getNearestValuesCellIDs(x, y, cellRowIndex, cellColIndex, _NoDataValue, HOOME);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValuesCellIDs method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValuesCellIDs_5args() {
//        System.out.println("getNearestValuesCellIDs");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double _NoDataValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Grids_2D_ID_long[] expResult = null;
//        Grids_2D_ID_long[] result = instance.getNearestValuesCellIDs(x, y, cellRowIndex, cellColIndex, _NoDataValue);
//        assertArrayEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDoubleDistance method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDoubleDistance_3args_1() {
//        System.out.println("getNearestValueDoubleDistance");
//        double x = 0.0;
//        double y = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDoubleDistance(x, y, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDoubleDistance method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDoubleDistance_double_double() {
//        System.out.println("getNearestValueDoubleDistance");
//        double x = 0.0;
//        double y = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDoubleDistance(x, y);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDoubleDistance method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDoubleDistance_3args_2() {
//        System.out.println("getNearestValueDoubleDistance");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDoubleDistance(cellRowIndex, cellColIndex, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDoubleDistance method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDoubleDistance_long_long() {
//        System.out.println("getNearestValueDoubleDistance");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDoubleDistance(cellRowIndex, cellColIndex);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDoubleDistance method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDoubleDistance_6args() {
//        System.out.println("getNearestValueDoubleDistance");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double noDataValue = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDoubleDistance(x, y, cellRowIndex, cellColIndex, noDataValue, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNearestValueDoubleDistance method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testGetNearestValueDoubleDistance_5args() {
//        System.out.println("getNearestValueDoubleDistance");
//        double x = 0.0;
//        double y = 0.0;
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double noDataValue = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.getNearestValueDoubleDistance(x, y, cellRowIndex, cellColIndex, noDataValue);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addToCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testAddToCell_4args_1() {
//        System.out.println("addToCell");
//        double x = 0.0;
//        double y = 0.0;
//        double valueToAdd = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.addToCell(x, y, valueToAdd, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addToCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testAddToCell_3args_1() {
//        System.out.println("addToCell");
//        double x = 0.0;
//        double y = 0.0;
//        double valueToAdd = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.addToCell(x, y, valueToAdd);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addToCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testAddToCell_3args_2() {
//        System.out.println("addToCell");
//        Grids_2D_ID_long cellID = null;
//        double valueToAdd = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.addToCell(cellID, valueToAdd, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addToCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testAddToCell_Grids_2D_ID_long_double() {
//        System.out.println("addToCell");
//        Grids_2D_ID_long cellID = null;
//        double valueToAdd = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.addToCell(cellID, valueToAdd);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addToCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testAddToCell_4args_2() {
//        System.out.println("addToCell");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double valueToAdd = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.addToCell(cellRowIndex, cellColIndex, valueToAdd, HOOME);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of addToCell method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testAddToCell_3args_3() {
//        System.out.println("addToCell");
//        long cellRowIndex = 0L;
//        long cellColIndex = 0L;
//        double valueToAdd = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        double expResult = 0.0;
//        double result = instance.addToCell(cellRowIndex, cellColIndex, valueToAdd);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testInitCells_double_boolean() {
//        System.out.println("initCells");
//        double value = 0.0;
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        instance.initCells(value, HOOME);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of initCells method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testInitCells_double() {
//        System.out.println("initCells");
//        double value = 0.0;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        instance.initCells(value);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of iterator method, of class Grids_GridDouble.
//     */
//    @Test
//    public void testIterator() {
//        System.out.println("iterator");
//        boolean HOOME = false;
//        Grids_GridDouble instance = new Grids_GridDouble();
//        Iterator<Double> expResult = null;
//        Iterator<Double> result = instance.iterator(HOOME);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
