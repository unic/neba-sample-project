echo
echo "Building sample app..."
echo

mvn -B clean install || exit 1

echo
echo "Building sling launchpad (this may take some time)..."
echo
pushd launchpad || exit 1
mvn -B clean install || exit 1
popd || exit 1

echo
echo "Starting docker container. It will take some time for Sling to initialize."
echo
docker-compose up -d --force-recreate --remove-orphans || exit 1

echo
echo Awaiting sling startup...
echo

until [ "$(curl --silent -u admin:admin http://localhost:8080/system/console/modelregistry | grep -c 'to.adapt.neba.api.models.neba.Page')" -eq 1 ]
do
    printf '.'
    sleep 5
done

echo
echo The sample app started successfully - build success, shutting down.
echo
docker-compose down --remove-orphans
