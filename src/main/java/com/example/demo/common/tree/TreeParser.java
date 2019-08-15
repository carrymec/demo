package com.example.demo.common.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @CLASSNAME TreeParser
 * @DESCRIPTION 描述
 * @AUTHOR chen
 * @DATE 2018-10-15 17:53
 **/
public class TreeParser {

    public static <E extends TreeEntity <E>> List <E> getTreeList(Integer topId, List <E> entityList) {
        List <E> resultList = new ArrayList <>();
        //获取顶层元素集合
        entityList.forEach( en -> {
            Integer parentId;
            parentId = en.getParentId();
            if (parentId == null || topId.equals( parentId )) {
                resultList.add( en );
            }
        } );
        //获取每个顶层元素的子数据集合
        resultList.forEach( entity -> {
            entity.setChildList( getSubList( entity.getDepartmentId(), entityList ) );
        } );
        return resultList;
    }

    private static <E extends TreeEntity <E>> List <E> getSubList(Integer id, List <E> entityList) {
        List <E> childList = new ArrayList <>();
//        //子集的直接子对象
        entityList.forEach( e -> {
            Integer parentId;
            parentId = e.getParentId();
            if (id.equals( parentId )) {
                childList.add( e );
            }
        } );


        //子集的间接子对象
        childList.forEach( c -> {
            c.setChildList( getSubList( c.getDepartmentId(), entityList ) );
        } );
        //递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
