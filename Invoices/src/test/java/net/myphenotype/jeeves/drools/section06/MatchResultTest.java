/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package net.myphenotype.jeeves.drools.section06;

import net.myphenotype.jeeves.drools.Common;
import net.myphenotype.jeeves.drools.TestUtil;
import net.myphenotype.jeeves.drools.domain.MatchingOutcome;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

class MatchResultTest {

  @Test
  void shouldExecuteAllSteps() {
    MatchResult.execute(1);
    MatchResult.execute(2);
    MatchResult.execute(3);
    MatchResult.execute(4);

    MatchResult.execute(5);

    MatchResult.execute(6);
  }

  @Test
  void testStep1_testItDoesntFail() {
    int step = 1;

    String kieSessionName = "MatchResultStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    MatchResult.execute(step);
  }

  @Test
  void testStep2() {
    int step = 2;

    Common.disposeSession = false;
    MatchResult.execute(step);

    KieSession ksession = TestUtil.getKieSession("MatchResultStep", step);

    List<MatchingOutcome> matchingOutcomes = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, matchingOutcomes, MatchingOutcome.class);

    assertThat(matchingOutcomes.size(), CoreMatchers.is(equalTo(1)));
    assertThat(matchingOutcomes.get(0).getInvoiceNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));
  }

  @Test
  void testStep3() {
    int step = 3;

    Common.disposeSession = false;
    MatchResult.execute(step);

    KieSession ksession = TestUtil.getKieSession("MatchResultStep", step);

    List<MatchingOutcome> matchingOutcomes = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, matchingOutcomes, MatchingOutcome.class);

    assertThat(matchingOutcomes.size(), CoreMatchers.is(equalTo(1)));
    assertThat(matchingOutcomes.get(0).getInvoiceNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));
  }

  @Test
  void testStep4() {
    int step = 4;

    Common.disposeSession = false;
    MatchResult.execute(step);

    KieSession ksession = TestUtil.getKieSession("MatchResultStep", step);

    List<MatchingOutcome> matchingOutcomes = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, matchingOutcomes, MatchingOutcome.class);

    assertThat(matchingOutcomes.size(), CoreMatchers.is(equalTo(1)));
    assertThat(matchingOutcomes.get(0).getInvoiceNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));
  }

  @Test
  void testStep5_noAnswer() {
    int step = 5;
    String kieSessionName = "MatchResultStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    Common.disposeSession = false;

    MatchResult.execute(step);

    KieSession ksession = TestUtil.getKieSession(kieSessionName);

    List<MatchingOutcome> matchingOutcomes = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, matchingOutcomes, MatchingOutcome.class);

    ksession.dispose();

    assertThat(matchingOutcomes.size(), CoreMatchers.is(equalTo(2)));
    assertThat(matchingOutcomes.stream().map(MatchingOutcome::getInvoiceNumber).collect(Collectors.toList()),
            containsInAnyOrder("AU-EMILY-3", "CA-SIMON-2"));
  }

  @Test
  void testStep5_yesAnswer() {
    int step = 5;
    String kieSessionName = "MatchResultStep" + step;
    TestUtil.disposeKieSessionIfExists(kieSessionName);

    Common.disposeSession = false;

    MatchResult.execute(step);

    KieSession ksession = TestUtil.getKieSession(kieSessionName);

    List<MatchingOutcome> matchingOutcomes = new ArrayList<>();
    TestUtil.addObjectsOfType(ksession, matchingOutcomes, MatchingOutcome.class);

    ksession.dispose();

    assertThat(matchingOutcomes.size(), CoreMatchers.is(equalTo(0)));
  }

  @Test
  void testStep6() {
    int step = 6;

    Common.disposeSession = false;
    MatchResult.execute(step);

    KieSession ksession = TestUtil.getKieSession("MatchResultStep", step);

    List<MatchingOutcome> matchingOutcomes = new ArrayList<>();

    TestUtil.addObjectsOfType(ksession, matchingOutcomes, MatchingOutcome.class);

    assertThat(matchingOutcomes.size(), CoreMatchers.is(equalTo(1)));
    assertThat(matchingOutcomes.get(0).getInvoiceNumber(), CoreMatchers.is(equalTo("AU-EMILY-3")));
  }

}
