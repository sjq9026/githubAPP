package com.sjq.githubapp.util;

import android.text.TextUtils;
import android.util.Log;

public class UtilLog {


    /**
     * Created by cs001 on 2016/9/12.
     */

        private static boolean isDebug = true;
        private static String TAG = "TAG";

        public static void init(boolean isDebug, String tag) {
            UtilLog.isDebug = isDebug;
            UtilLog.TAG = tag;
        }


        public static void i(String msg, Object... params) {
            i(null, msg, params);
        }


        public static void i(String tag, String msg, Object[] params) {
            if (!isDebug) {
                return;
            }
            TAG = getFindTag(tag);
            StackTraceElement element = getTargetStackTrackElement();
            //TODO 通过stackelement获得打印的行数
            android.util.Log.i(TAG, msg + "             " + "(" + element.getFileName() + ":" + element.getLineNumber() + ")");
        }

        private static String getFindTag(String tag) {
            if (!TextUtils.isEmpty(tag)) {
                return tag;
            }
            return TAG;
        }

        /**
         * 遍历栈,如果当前栈对象的类名和此类的类名不一致
         *
         * @return
         */
        private static StackTraceElement getTargetStackTrackElement() {
            StackTraceElement targetStackTrace = null;
            boolean shouldTrace = false;
            //你可以理解为当我们调用方法的时候，每进入一个方法，
            // 会将该方法的相关信息（例如：类名，方法名，方法调用行数等）
            // 存储下来，压入到一个栈中，当方法返回的时候再将其出栈。
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            StringBuffer sb = new StringBuffer();
            for (StackTraceElement stackTraceElement : stackTrace) {
                sb.append(stackTraceElement.toString() + "\n");
                boolean isLogMethod = stackTraceElement.getClassName().equals(Log.class.getName());
                android.util.Log.i(TAG, stackTraceElement.getClassName() + "   " + stackTraceElement.getMethodName());
                /**alvik.system.VMStack.getThreadStackTrace(Native Method) false  !false
                 java.lang.Thread.getStackTrace(Thread.java:579)    false   !false
                 com.sjq.locationlog.Log.getTargetStackTrackElement(Log.java:48)  false   !false
                 com.sjq.locationlog.Log.i(Log.java:28)     false  !true
                 com.sjq.locationlog.Log.i(Log.java:19)     true   !true
                 com.sjq.locationlog.MainActivity.b(MainActivity.java:45) true !false  */
                if (shouldTrace && !isLogMethod) {
                    targetStackTrace = stackTraceElement;
                    break;
                }
                shouldTrace = isLogMethod;
            }
            return targetStackTrace;
        }



}
