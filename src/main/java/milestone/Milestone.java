package milestone;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Milestone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "milestone_id")
	private Long id;

	private String title;

	private String description;

	private LocalDate completionDate;

	@Column(name = "member_id")
	private Long authorId;

}
