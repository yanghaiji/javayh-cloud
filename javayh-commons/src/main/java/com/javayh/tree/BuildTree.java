package com.javayh.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dylan Yang
 * @Description: TODO
 * @Title: BuildTree
 * @ProjectName javayh-oauth2
 * @date 2019/5/20 21:29
 */
public class BuildTree {

    List<TreeNode> TreeNodes = new ArrayList<>();

    public List<TreeNode> buildTree(List<TreeNode> TreeNodes) {
        BuildTree treeBuilder = new BuildTree(TreeNodes);
        return treeBuilder.buildJSONTree();
    }

    public BuildTree() {
    }

    public BuildTree(List<TreeNode> TreeNodes) {
        super();
        this.TreeNodes = TreeNodes;
    }

    // 构建JSON树形结构
    public List<TreeNode> buildJSONTree() {
        List<TreeNode> TreeNodeTree = buildTree();
        return TreeNodeTree;
    }

    // 构建树形结构
    public List<TreeNode> buildTree() {
        List<TreeNode> treeTreeNodes = new ArrayList<>();
        //获取所有根节点
        List<TreeNode> rootTreeNodes = getRootTreeNodes();
        //获取每个根节点
        for (TreeNode rootTreeNode : rootTreeNodes) {
            try {
                //递归这个根节点的子节点
                buildChildTreeNodes(rootTreeNode);

                //用list来存放每个根节点
                treeTreeNodes.add(rootTreeNode);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return treeTreeNodes;
    }

    // 递归子节点
    public void buildChildTreeNodes(TreeNode TreeNode) {
        List<TreeNode> treeNodeList = getChildTreeNodes(TreeNode);
        if (!treeNodeList.isEmpty()) {
            for (TreeNode child : treeNodeList)
                try {
                    buildChildTreeNodes(child);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            TreeNode.setTreeNodeList(treeNodeList);
        }
    }

    /**
     * 获取父节点下所有的子节点
     * @param pTreeNode
     * @return
     */
    public List<TreeNode> getChildTreeNodes(TreeNode pTreeNode) {
        List<TreeNode> childTreeNodes = new ArrayList<>();

        for (TreeNode n : TreeNodes) {
            if (pTreeNode.getOfficeCode().equals(n.getParentCode())) {
                childTreeNodes.add(n);
            }
        }
        return childTreeNodes;
    }

    // 判断是否为根节点
    public boolean rootTreeNode(TreeNode TreeNode) {
        boolean isRootTreeNode = true;
        for (TreeNode n : TreeNodes) {
            if (TreeNode.getParentCode().equals(n.getOfficeCode())) {
                isRootTreeNode = false;
                break;
            }
        }
        return isRootTreeNode;
    }

    // 获取集合中所有的根节点
    public List<TreeNode> getRootTreeNodes() {
        List<TreeNode> rootTreeNodes = new ArrayList<>();
        for (TreeNode n : TreeNodes) {
            if (rootTreeNode(n)) {
                rootTreeNodes.add(n);
            }
        }
        return rootTreeNodes;
    }
}
