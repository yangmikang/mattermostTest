@rem	author : wonsorang
@rem 	SVN WC_LOCK ����

@echo off

IF NOT EXIST ".svn" (
	ECHO .svn ������ �������� �ʽ��ϴ�.
	GOTO INVALID_PATH
)

@echo on
sqlite3.exe .svn/wc.db "select * from WC_LOCK"
sqlite3.exe .svn/wc.db "select * from WORK_QUEUE"
@echo off

ECHO SVN WC_LOCK, WORK_QUEUE �� �����մϴ�.

PAUSE

@echo on
sqlite3.exe .svn/wc.db "delete from WC_LOCK"
sqlite3.exe .svn/wc.db "delete from WORK_QUEUE"
@echo off

ECHO SVN WC_LOCK, WORK_QUEUE ���� �Ϸ�!

GOTO END

:INVALID_PATH

ECHO .svn �� �����ϴ� ��ο��� �������ּ���!

:END

PAUSE