package demo.entities;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

public
@RunWith(Arquillian.class)
class ProjectDaoTest {
    @Inject
    private ProjectDao projectdao;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")
                .addClass(ProjectDao.class)
                .addClass(Project.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

    @Test
    public void testIsDeployed() {
        Assert.assertNotNull(projectdao);
    }

    @Test
    public void testListProject() {
        List<Project> projects = projectdao.listProject();
        assertThat(projects.size(), is(0));
    }
}
