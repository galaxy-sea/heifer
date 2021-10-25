package plus.wcj.heifer.boot.utils;


import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/15
 */
public class TreeUtils {

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
                if (id.equals(getParentId.apply(element))) {
                    elements.add(element);
                }
                if (isParent && getId.apply(element).equals(parentId)) {
                    isParent = false;
                }
            }
            if (isParent) {
                root.add(node);
            }
            setNode.accept(node, elements);
        }
        return root;
    }
}
