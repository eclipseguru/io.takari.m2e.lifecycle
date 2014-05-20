package io.takari.m2e.jdt.core.internal;

import java.util.List;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.m2e.jdt.internal.AbstractJavaProjectConfigurator;

@SuppressWarnings("restriction")
public class JavaConfigurator extends AbstractJavaProjectConfigurator {

  private static final String COMPILER_PLUGIN_GROUP_ID = "io.takari.maven.plugins";

  private static final String COMPILER_PLUGIN_ARTIFACT_ID = "takari-lifecycle-plugin";

  private static final String GOAL_COMPILE = "compile";

  private static final String GOAL_TESTCOMPILE = "testCompile";

  @Override
  protected List<MojoExecution> getCompilerMojoExecutions(ProjectConfigurationRequest request,
      IProgressMonitor monitor) throws CoreException {
    return request.getMavenProjectFacade().getMojoExecutions(COMPILER_PLUGIN_GROUP_ID,
        COMPILER_PLUGIN_ARTIFACT_ID, monitor, GOAL_COMPILE, GOAL_TESTCOMPILE);
  }

  @Override
  protected boolean isTestCompileExecution(MojoExecution execution) {
    return GOAL_TESTCOMPILE.equals(execution.getGoal());
  }

  @Override
  protected boolean isCompileExecution(MojoExecution execution) {
    return GOAL_COMPILE.equals(execution.getGoal());
  }

}