#!/bin/sh

FILE_COMMIT_MSG=.git/hooks/commit-msg
cp ./hooks/commit-msg $FILE_COMMIT_MSG
chmod +x $FILE_COMMIT_MSG
echo "commit-msg file generated at ${FILE_COMMIT_MSG}"

FILE_PRE_PUSH=.git/hooks/pre-push
cp ./hooks/pre-push $FILE_PRE_PUSH
chmod +x $FILE_PRE_PUSH
echo "pre-push file generated at ${FILE_PRE_PUSH}"
exec <&-
