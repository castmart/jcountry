version := "0.0.2"

target_data_directory := lib/src/main/resources/
target_po_directory := lib/src/main/resources/iso_3166/
target_po_639_directory := lib/src/main/resources/iso_639/

.PHONY: clone-iso-codes
clone-iso-codes:
	git clone https://github.com/sailfishos-mirror/iso-codes.git
	cp iso-codes/data/iso_3166-1.json $(target_data_directory)/3166-1.json
	cp iso-codes/iso_3166-1/*.po $(target_po_directory)
	cp iso-codes/iso_3166-1/*.pot $(target_po_directory)
	cp iso-codes/data/iso_639-2.json $(target_data_directory)/639-2.json
	cp iso-codes/iso_639-2/*.po $(target_po_639_directory)
	cp iso-codes/iso_639-2/*.pot $(target_po_639_directory)
	rm -Rf iso-codes

.PHONY: transpile-translations
transpile-translations:
	bash ./transpile_po_files.sh $(target_po_directory) iso_3166-1
	bash ./transpile_po_files.sh $(target_po_639_directory) iso_639-2

.PHONY: delete-po-and-mo
delete-po-and-mo:
	rm $(target_po_directory)*.po
	rm $(target_po_directory)*.mo
	rm $(target_po_639_directory)*.mo
	rm $(target_po_639_directory)*.po

.PHONY: get-latest-iso-files
get-latest-iso-files: clone-iso-codes transpile-translations delete-po-and-mo

# Generating deployable
.PHONY: generate-artifacts
generate-artifacts:
	JCOUNTRY_VERSION=${version} ./gradlew build

.PHONY: generate-pom
publish-to-ossrh:
	OSSRH_USER=${OSSRH_USER} \
	OSSRH_PASS=${OSSRH_PASS} \
	GPG_SIGNING_KEY_PASSWORD=${GPG_SIGNING_KEY_PASSWORD} \
	GPG_SIGNING_KEY=${GPG_SIGNING_KEY} \
	JCOUNTRY_VERSION=${version} \
	./gradlew publish
