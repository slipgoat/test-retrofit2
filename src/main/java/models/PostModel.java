package models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostModel extends BaseModel {
    private Long userId;
    private Long id;
    private String title;
    private String body;

    public PostModel(String title, String body, Long userId) {
        this.id = 0L;
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
