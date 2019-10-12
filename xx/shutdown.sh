ps -ef | grep ding-dang-cat | grep -v grep | awk '{print $2}' | xargs kill -9
