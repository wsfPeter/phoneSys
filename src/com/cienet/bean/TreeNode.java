package com.cienet.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TreeNode
 * @Package com.cienet.bean
 * @Description:
 * @author 詹涛
 * @date 2014/8/27 15:16
 * @version V1.0
 */
public class TreeNode {

    private String id;
    private String name;
    private boolean isParent;
    private boolean checked;
    private String tag;
    private List<TreeNode> children = new ArrayList<TreeNode>();

    public List<TreeNode> getChildren() {
        return children;
    }

    public String getId() {
        return id;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
