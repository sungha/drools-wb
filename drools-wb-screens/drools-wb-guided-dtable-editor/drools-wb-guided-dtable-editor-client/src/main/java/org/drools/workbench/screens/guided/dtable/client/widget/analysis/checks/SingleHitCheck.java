/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.guided.dtable.client.widget.analysis.checks;

import com.google.gwt.safehtml.shared.SafeHtml;
import org.drools.workbench.screens.guided.dtable.client.resources.i18n.AnalysisConstants;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.RuleInspector;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.Explanation;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.ExplanationProvider;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.Issue;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.Severity;

public class SingleHitCheck {

    public static Issue check( final RuleInspector ruleInspector,
                               final RuleInspector other ) {

        if ( ruleInspector.getConditionsInspectors().subsumes( other.getConditionsInspectors() ) ) {
            return getIssue( ruleInspector,
                             other );
        } else {
            return Issue.EMPTY;
        }
    }

    public static Issue getIssue( final RuleInspector ruleInspector,
                                  final RuleInspector other ) {
        final Issue issue = new Issue( Severity.NOTE,
                                       AnalysisConstants.INSTANCE.SingleHitLost(),
                                       new ExplanationProvider() {
                                           @Override
                                           public SafeHtml toHTML() {
                                               return new Explanation()
                                                       .addParagraph( AnalysisConstants.INSTANCE.SingleHitP1( ruleInspector.getRowIndex() + 1,
                                                                                                              other.getRowIndex() + 1 ) )
                                                       .toHTML();
                                           }
                                       } );

        return issue;
    }
}
