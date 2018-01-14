REM  使用时需安装mvn工具
REM  也可直接在Eclipse中运行此命令进行注册
mvn install:install-file -Dfile=IKAnalyzer-2012.jar -DgroupId=org.wltea -DartifactId=IKAnalyzer -Dversion=2012 -DcreateChecksum=true -Dpackaging=jar -DgeneratePom=true
pause
