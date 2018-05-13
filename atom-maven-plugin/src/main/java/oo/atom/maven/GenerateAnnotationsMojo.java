package oo.atom.maven;

import oo.atom.codegen.javapoet.JpAtomAnnotation;
import oo.atom.codegen.javapoet.JpNotAtomAnnotation;
import oo.atom.instrumentation.GenerateSources;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.nio.file.Paths;

@Mojo(name = "generate-annotations", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class GenerateAnnotationsMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project.basedir}/target/generated-sources/annotations", required = true, readonly = true)
    private String workingDirectory;

    @Override
    public final void execute() throws MojoExecutionException, MojoFailureException {
        new GenerateSources(
            Paths.get(workingDirectory),
            new JpAtomAnnotation(),
            new JpNotAtomAnnotation()
        ).apply();
    }
}
