package plus.wcj.heifer.boot.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/15
 */
public class TreeUtils {

    private TreeUtils() {
    }

    /**
     * 找不到 Parent 节点的元素 删除<br/>
     * A<br/>
     * AB<br/>
     * BA BB BC<br/>
     */
    public static <T extends Tree<T>> Collection<T> terr(Collection<T> elements) {
        Collection<T> root = new HashSet<>();
        for (T node : elements) {
            if (null == node.treeParentId() || "0".equals(node.treeParentId().toString())) {
                root.add(node);
            }
            for (T element : elements) {
                if (node.treeId().equals(element.treeParentId())) {
                    if (node.treeNode() == null) {
                        node.treeNode(new LinkedList<>());
                    }
                    node.treeNode().add(element);
                }
            }
        }
        return root;
    }

    /**
     * 找不到Parent节点的元素成为Parent<br/>
     * A            CA          E<br/>
     * AB           DA DB<br/>
     * BA BB BC<br/>
     */
    public static <T extends Tree<T>> Collection<T> zTerr(Collection<T> elements) {
        Collection<T> root = new HashSet<>();
        for (T element : elements) {
            if (null == element.treeParentId() || "0".equals(element.treeParentId().toString())) {
                root.add(element);
                continue;
            }
            boolean flag = false;
            for (T node : elements) {
                if (node.treeId().equals(element.treeParentId())) {
                    node.treeNode().add(element);
                    flag = true;
                }
            }
            if (!flag) {
                root.add(element);
            }
        }
        return root;
    }


    public interface Tree<E extends Tree<?>> {
        /**
         * 获取id
         *
         * @return id
         */
        Object treeId();

        /**
         * 获取父ID，只能返回 NULL或者0
         *
         * @return parentId
         */
        Object treeParentId();

        /**
         * 获取 字节点
         *
         * @return Node
         */
        Collection<E> treeNode();

        /**
         * 设置 子节点
         *
         * @param node 子节点
         *
         */
        void treeNode(Collection<E> node);
    }
}
