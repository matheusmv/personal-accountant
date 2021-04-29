package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wallet {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private String userId;
}
