package plus.wcj.heifer.boot.extension.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

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
    private List<TreeDTO<E>> elements;

    public TreeDTO(Long id, Long parentId, E node) {
        this.id = id;
        this.parentId = parentId;
        this.node = node;
        this.elements = new ArrayList<>();
    }


    public static <T> List<TreeDTO<T>> sort(List<TreeDTO<T>> elements) {

        List<TreeDTO<T>> root = new ArrayList<>();

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
}
