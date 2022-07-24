package milestone;

import com.example.clonegithubissue.issue.Issue;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "milestone")
	private Set<Issue> issues = new HashSet<>();

	@Column(name = "member_id")
	private Long authorId;

}
