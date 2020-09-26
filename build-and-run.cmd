echo off
echo.
echo Note: This script requires that docker-compose and mvn are available at the command line.
echo.
echo.
echo Building sample app...
echo.

CALL mvn clean install

echo.
echo Building sling launchpad (this may take some time)...
echo.
pushd launchpad
CALL mvn clean install
popd

echo.
echo Starting docker container. It will take some time for sling to initialize.
echo.
docker-compose up --force-recreate --remove-orphans
