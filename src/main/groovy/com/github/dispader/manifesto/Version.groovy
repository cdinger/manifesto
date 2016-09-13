package com.github.dispader.manifesto

import org.gradle.api.ProjectConfigurationException

class Version {

    private static getGit() {
        try {
            org.ajoberstar.grgit.Grgit.open()
        } catch(Exception ex) {
            throw new ProjectConfigurationException('This project is not controlled by git.')
        }
    }

    static getVersion() {
        String description = git?.describe { }
        if ( !description ) {
            throw new ProjectConfigurationException('This project is not controlled by git.')
        }
        description.startsWith('v') ? description.substring(1) : description
    }

    static getImplementation() { Version.version ?: '' }
    static getSpecification() { implementation?.split(/-\d/)[0] ?: '' }

}
