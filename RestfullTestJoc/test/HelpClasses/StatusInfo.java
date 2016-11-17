/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelpClasses;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author justinas
 */
@XmlRootElement
public class StatusInfo {
    public static enum Status {
		UNCONFIRMED,
		CONFIRMED,
		CANCELLED,
	}
}
