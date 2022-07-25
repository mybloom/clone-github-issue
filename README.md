# Github Issue Clone Project

---

## Skill
- JDK 11
- Spring Boot 2.6.6
- Mysql 8.0.28
- Spring Data JPA
- Gradle

---
## Installation
```
git clone https://github.com/mybloom/clone-github-issue.git
cd clone-github-issue
chmod +x gradlew
./gradlew build
nohup java -jar clone-github-issue-0.0.1.jar &
```
---

## Project Requirements

### Development Scope
- Issue, Milestone, Label
- Authentication using OAuth and JWT

### Special Note
- Assume there is one virtual Repository per account.
- Issue can only be created in own repository.

### Feature List
- [ ] Login Service.
- [ ] Milestones, labels, and issue assigners can be specified when creating an issue.
- [ ] Only the issue creator can create/modify/delete the milestone and label of the issue.
- [ ] Issue assignees can modify issues.

---

##  API Specification
- [Label API Specification](./docs/apiLabel.md)
- [Milestone API Specification](./docs/apiMilestone.md)

