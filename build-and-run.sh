echo "Note: This script requires that docker-compose and mvn are available at the command line."
echo
echo "Building sample app..."
echo

mvn clean install || exit 1

echo
echo "Building sling launchpad (this may take some time)..."
echo
pushd launchpad || exit 1
mvn clean install || exit 1
popd || exit 1

echo "Starting docker container. It will take some time for Sling to initialize."
docker-compose up --force-recreate --remove-orphans || exit 1
