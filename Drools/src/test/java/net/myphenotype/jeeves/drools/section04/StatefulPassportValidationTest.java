/*
 *  Drools Online Course Sample Code and Study Materials (c) by Juhan Aasaru.
 *
 *  Drools Online Course Sample Code and Study Materials is licensed under a
 *  Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.
 *
 *  You should have received a copy of the license along with this
 *  work. If not, see <http://creativecommons.org/licenses/by-nc-nd/4.0/>.
 */

package net.myphenotype.jeeves.drools.section04;

import org.junit.jupiter.api.Test;

class StatefulPassportValidationTest {

  @Test
  void shouldExecuteAllSteps() {
    StatefulPassportValidation.execute(1);
    StatefulPassportValidation.execute(2);
  }

}
