echo "Building sample app..."
mvn clean install || exit 1

echo
echo "Building sling launchpad (this may take some time)..."
echo
pushd launchpad || exit 1
mvn clean install || exit 1
popd || exit 1
