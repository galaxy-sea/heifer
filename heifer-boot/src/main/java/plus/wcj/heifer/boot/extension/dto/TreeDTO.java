package plus.wcj.heifer.boot.extension.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/15
 */
@Data
public class TreeDTO<E> {

    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long parentId;
    private E node;
    @EqualsAndHashCode.Exclude
    private Collection<TreeDTO<E>> elements;

    public TreeDTO(Long id, Long parentId, E node) {
        this.id = id;
        this.parentId = parentId;
        this.node = node;
        this.elements = new HashSet<>();
    }

    /**
     * 找不到 Parent 节点的元素 删除<br/>
     * A<br/>
     * AB<br/>
     * BA BB BC<br/>
     */
    public static <T> Collection<TreeDTO<T>> terr(Collection<TreeDTO<T>> elements) {
        Collection<TreeDTO<T>> root = new HashSet<>();
        for (TreeDTO<T> node : elements) {
            if (null == node.getParentId() || 0L == node.getParentId() || 0 == node.getParentId()) {
                root.add(node);
            }
            for (TreeDTO<T> element : elements) {
                if (node.getId().equals(element.getParentId())) {
                    node.getElements().add(element);
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
    public static <T> Collection<TreeDTO<T>> zTerr(Collection<TreeDTO<T>> elements) {
        Collection<TreeDTO<T>> root = new HashSet<>();
        for (TreeDTO<T> element : elements) {
            if (null == element.getParentId() || 0 == element.getParentId()) {
                root.add(element);
                continue;
            }
            boolean flag = false;
            for (TreeDTO<T> node : elements) {
                if (node.getId().equals(element.getParentId())) {
                    node.getElements().add(element);
                    flag = true;
                }
            }
            if (!flag) {
                root.add(element);
            }
        }
        return root;
    }
}
