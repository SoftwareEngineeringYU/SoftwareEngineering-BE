## Branch Convention

```
main ── develop ── feature
└── hotfix
```
  
1. branch 딸 때, 구현할 기능 issue에 먼저 등록 
2. `git checkout -b feat/이슈번호`로 브랜치 생성 후 작업
3. 작업 끝나면 본인 브랜치에 `git push`하고 PR 작성. (작업 상황 공유를 위해 draft PR 주기적으로 작성 요망)
4. 최소 2명에게 리뷰받고 승인 후 Merge하고 branch 삭제
