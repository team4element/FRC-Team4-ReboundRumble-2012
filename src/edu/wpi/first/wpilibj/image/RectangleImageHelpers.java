/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.image;

import com.sun.cldc.jna.*;
/**
 *
 * @author JULIAN
 * @author JUSTIN
 */
public class RectangleImageHelpers {
    private static final TaskExecutor taskExecutor = new TaskExecutor("nivision task");;

    private static void assertCleanStatus(int code) throws NIVisionException {
        if (code == 0)
            throw new NIVisionException(NIVision.getLastError());
    }
    
    private RectangleImageHelpers() {};
    
    private static final BlockingFunction imaqConvexHullFn = NativeLibrary.getDefaultInstance().getBlockingFunction("imaqConvexHull");
    static { imaqConvexHullFn.setTaskExecutor(taskExecutor); }
    public static void convexHull(Pointer source, Pointer dest, boolean connect8)  throws NIVisionException{
        assertCleanStatus(imaqConvexHullFn.call3(source.address().toUWord().toPrimitive(), dest.address().toUWord().toPrimitive(), connect8?0:1));
    }    
    
    public static void convexHull(BinaryImage image, boolean connect8) throws NIVisionException {
        convexHull(image.image, image.image, connect8);
    }
}
