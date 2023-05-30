package leets.crazyform.domain.creator.domain;

import jakarta.persistence.*;
import leets.crazyform.domain.shared.entity.BaseCreateTimeEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "creator_access_logs")
public class CreatorAccessLog extends BaseCreateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "CHAR(25)")
    private String ip;

    @Column
    private String userAgent;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private Creator creator;
}
