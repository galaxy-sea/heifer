package plus.wcj.heifer.tools.utils;


import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/15
 */
public final class TreeUtils {

    private TreeUtils() {
    }

    /**
     * @param collection this is a collection of elements
     * @param getId this is a getId Function
     * @param getParentId this is a getParentId Function
     * @param setNode this is a setNode BiConsumer
     * @param <E> the type of elements in this collection
     * @param <R> the type of the result of the function
     *
     * @return Collection
     */
    public static <E, R> Collection<E> tree(Collection<E> collection, Function<E, R> getId, Function<E, R> getParentId, BiConsumer<E, Collection<E>> setNode) {
        Collection<E> root = new LinkedList<>();
        for (E node : collection) {
            R parentId = getParentId.apply(node);
            R id = getId.apply(node);
            Collection<E> elements = new LinkedList<>();
            boolean isParent = true;
            for (E element : collection) {
                if (Objects.equals(id, getParentId.apply(element))) {
                    elements.add(element);
                }
                if (isParent && Objects.equals(parentId, getId.apply(element))) {
                    isParent = false;
                }
            }
            if (isParent) {
                root.add(node);
            }
            if (!elements.isEmpty()) {
                setNode.accept(node, elements);
            }
        }
        return root;
    }


    public static <E, R> E tree(Collection<E> collection, Function<E, R> getId, Function<E, R> getParentId, BiConsumer<E, Collection<E>> setNode, R rootId) {
        E root = null;
        for (E node : collection) {
            R id = getId.apply(node);
            Collection<E> elements = new LinkedList<>();
            for (E element : collection) {
                if (Objects.equals(id, getParentId.apply(element))) {
                    elements.add(element);
                }
            }
            if (root == null && Objects.equals(id, rootId)) {
                root = node;
            }
            if (!elements.isEmpty()) {
                setNode.accept(node, elements);
            }
        }
        return root;
    }
}
