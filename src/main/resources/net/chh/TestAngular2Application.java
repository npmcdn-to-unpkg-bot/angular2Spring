package net.chh;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Angular2Application.class)
public class TestAngular2Application {

  @Autowired
  private Angular2Application app;

  @Test
  public void test() {
    final Map<String, Object> home = app.home();
    assertNotNull(home.get("id"));
    assertNotNull(home.get("content"));
  }
}
