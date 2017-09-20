/*
 * Copyright (C) 2017 agdturner.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

package uk.ac.leeds.ccg.andyt.grids.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Andy
 */
public interface Grids_OutOfMemoryErrorHandlerInterface
        extends Serializable {

    boolean tryToEnsureThereIsEnoughMemoryToContinue(
            boolean handleOutOfMemoryError);

    void tryToEnsureThereIsEnoughMemoryToContinue(
            Grids_AbstractGrid2DSquareCell g, 
            boolean handleOutOfMemoryError);

    void tryToEnsureThereIsEnoughMemoryToContinue(
            Grids_AbstractGrid2DSquareCell g, 
            Grids_2D_ID_int chunkNotToSwap, 
            boolean handleOutOfMemoryError);

    void tryToEnsureThereIsEnoughMemoryToContinue(
            Grids_2D_ID_int chunkNotToSwap, boolean handleOutOfMemoryError);

    void tryToEnsureThereIsEnoughMemoryToContinue(
            HashMap<Grids_AbstractGrid2DSquareCell, 
                    HashSet<Grids_2D_ID_int>> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    void tryToEnsureThereIsEnoughMemoryToContinue(
            Grids_AbstractGrid2DSquareCell g, 
            HashSet<Grids_2D_ID_int> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    long tryToEnsureThereIsEnoughMemoryToContinue_Account(
            boolean handleOutOfMemoryError);

    long tryToEnsureThereIsEnoughMemoryToContinue_Account(
            Grids_AbstractGrid2DSquareCell g, boolean handleOutOfMemoryError);

    long tryToEnsureThereIsEnoughMemoryToContinue_Account(
            Grids_AbstractGrid2DSquareCell g, 
            Grids_2D_ID_int chunkNotToSwap, 
            boolean handleOutOfMemoryError);

    long tryToEnsureThereIsEnoughMemoryToContinue_Account(
            Grids_2D_ID_int chunkNotToSwap, boolean handleOutOfMemoryError);

    long tryToEnsureThereIsEnoughMemoryToContinue_Account(
            HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    long tryToEnsureThereIsEnoughMemoryToContinue_Account(
            Grids_AbstractGrid2DSquareCell g, 
            HashSet<Grids_2D_ID_int> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> tryToEnsureThereIsEnoughMemoryToContinue_AccountDetail(
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> tryToEnsureThereIsEnoughMemoryToContinue_AccountDetail(
            Grids_AbstractGrid2DSquareCell g, boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> tryToEnsureThereIsEnoughMemoryToContinue_AccountDetail(
            Grids_AbstractGrid2DSquareCell g,
            Grids_2D_ID_int chunkNotToSwap, 
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> tryToEnsureThereIsEnoughMemoryToContinue_AccountDetail(
            Grids_2D_ID_int chunkNotToSwap, boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> tryToEnsureThereIsEnoughMemoryToContinue_AccountDetail(
            HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> chunksNotToSwap,
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> tryToEnsureThereIsEnoughMemoryToContinue_AccountDetail(
            Grids_AbstractGrid2DSquareCell g, 
            HashSet<Grids_2D_ID_int> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    void init_MemoryReserve(
            Grids_AbstractGrid2DSquareCell g, boolean handleOutOfMemoryError);

    void init_MemoryReserve(
            Grids_2D_ID_int chunkNotToSwap, 
            boolean handleOutOfMemoryError);

    void init_MemoryReserve(
            Grids_AbstractGrid2DSquareCell g, 
            Grids_2D_ID_int chunkNotToSwap,
            boolean handleOutOfMemoryError);

    void init_MemoryReserve(
            Grids_AbstractGrid2DSquareCell g,
            HashSet<Grids_2D_ID_int> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    void init_MemoryReserve(
            HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> chunksNotToSwap,
            boolean handleOutOfMemoryError);

    long init_MemoryReserve_Account(boolean handleOutOfMemoryError);

    long init_MemoryReserve_Account(
            Grids_2D_ID_int chunkNotToSwap, 
            boolean handleOutOfMemoryError);

    long init_MemoryReserve_Account(
            Grids_AbstractGrid2DSquareCell g,
            Grids_2D_ID_int chunkNotToSwap,
            boolean handleOutOfMemoryError);

    long init_MemoryReserve_Account(
            HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> chunksNotToSwap,
            boolean handleOutOfMemoryError);

    long init_MemoryReserve_Account(
            Grids_AbstractGrid2DSquareCell g, 
            boolean handleOutOfMemoryError);

    long init_MemoryReserve_Account(
            Grids_AbstractGrid2DSquareCell g, 
            HashSet<Grids_2D_ID_int> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> init_MemoryReserve_AccountDetail(
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> init_MemoryReserve_AccountDetail(
            Grids_2D_ID_int chunkNotToSwap, 
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> init_MemoryReserve_AccountDetail(
            Grids_AbstractGrid2DSquareCell g, 
            Grids_2D_ID_int chunkNotToSwap, 
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> init_MemoryReserve_AccountDetail(
            Grids_AbstractGrid2DSquareCell g, 
            HashSet<Grids_2D_ID_int> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> init_MemoryReserve_AccountDetail(
            Grids_AbstractGrid2DSquareCell g, 
            boolean handleOutOfMemoryError);

    HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> init_MemoryReserve_AccountDetail(
            HashMap<Grids_AbstractGrid2DSquareCell, HashSet<Grids_2D_ID_int>> chunksNotToSwap, 
            boolean handleOutOfMemoryError);

}
