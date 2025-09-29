@rem	author : wonsorang
@rem 	SVN WC_LOCK 제거

@echo off

IF NOT EXIST ".svn" (
	ECHO .svn 폴더가 존재하지 않습니다.
	GOTO INVALID_PATH
)

@echo on
sqlite3.exe .svn/wc.db "select * from WC_LOCK"
sqlite3.exe .svn/wc.db "select * from WORK_QUEUE"
@echo off

ECHO SVN WC_LOCK, WORK_QUEUE 를 제거합니다.

PAUSE

@echo on
sqlite3.exe .svn/wc.db "delete from WC_LOCK"
sqlite3.exe .svn/wc.db "delete from WORK_QUEUE"
@echo off

ECHO SVN WC_LOCK, WORK_QUEUE 제거 완료!

GOTO END

:INVALID_PATH

ECHO .svn 이 존재하는 경로에서 실행해주세요!

:END

PAUSE