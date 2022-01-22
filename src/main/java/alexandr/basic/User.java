package alexandr.basic;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class User {
    private final String name;
    private int score = 0;

    public void AddScore() {
        score += 1;
    }
}
