package com.aryanto.firstapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.aryanto.firstapp");

        noClasses()
            .that()
            .resideInAnyPackage("com.aryanto.firstapp.service..")
            .or()
            .resideInAnyPackage("com.aryanto.firstapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.aryanto.firstapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
