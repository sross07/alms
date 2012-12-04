/*******************************************************************************
 * Copyright (c) 2012 Scott Ross.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Scott Ross - initial API and implementation
 ******************************************************************************/
package org.alms.beans;

public class RelatedParty 
{
	private String NamespaceID;
	private String UniversialID;
	private String UniversialType;
	
	public String getNamespaceID() {
		return NamespaceID;
	}
	public void setNamespaceID(String namespaceID) {
		NamespaceID = namespaceID;
	}
	public String getUniversialID() {
		return UniversialID;
	}
	public void setUniversialID(String universialID) {
		UniversialID = universialID;
	}
	public String getUniversialType() {
		return UniversialType;
	}
	public void setUniversialType(String universialType) {
		UniversialType = universialType;
	}
}
