package com.imfondof.wanandroid.more.util;

import java.util.List;

public class ListUtil {
    public ListUtil() {
    }

    public static int getSize(List list) {
        return list != null && list.size() >= 1 ? list.size() : 0;
    }
}
