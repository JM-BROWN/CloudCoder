// CloudCoder - a web-based pedagogical programming environment
// Copyright (C) 2011-2012, Jaime Spacco <jspacco@knox.edu>
// Copyright (C) 2011-2012, David H. Hovemeyer <david.hovemeyer@gmail.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Affero General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Affero General Public License for more details.
//
// You should have received a copy of the GNU Affero General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package org.cloudcoder.builder2.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A Tester executes a series of {@link IBuildStep}s on a
 * {@link BuilderSubmission}.
 * 
 * @author David Hovemeyer
 */
public class Tester {
	private List<IBuildStep> buildStepList;
	
	/**
	 * Constructor.
	 */
	public Tester() {
		buildStepList = new ArrayList<IBuildStep>();
	}
	
	/**
	 * Add an {@link IBuildStep}.
	 * 
	 * @param buildStep the {@link IBuildStep} to add
	 */
	public void addBuildStep(IBuildStep buildStep) {
		buildStepList.add(buildStep);
	}
	
	/**
	 * Execute the Tester on a {@link BuilderSubmission}.
	 * 
	 * @param submission the {@link BuilderSubmission} to build/test
	 */
	public void execute(BuilderSubmission submission) {
		for (IBuildStep buildStep : buildStepList) {
			buildStep.execute(submission);
			if (submission.isComplete()) {
				break;
			}
		}
		
		if (!submission.isComplete()) {
			throw new InternalBuilderException("Executed all build steps but submission is not complete");
		}
	}
}
