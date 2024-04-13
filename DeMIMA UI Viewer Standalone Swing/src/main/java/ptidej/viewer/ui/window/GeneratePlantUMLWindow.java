/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Gaël Guéhéneuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     Yann-Gaël Guéhéneuc and others, see in file; API and its implementation
 ******************************************************************************/
package ptidej.viewer.ui.window;

import choco.Entity;
import padl.kernel.IConstituent;
import padl.kernel.IEntity;
import ptidej.ui.event.GraphEvent;
import ptidej.viewer.awt.entities.ListPanel;
import ptidej.viewer.event.SourceAndGraphModelEvent;
import ptidej.viewer.ui.AbstractExternalWindow;
import ptidej.viewer.ui.DesktopPane;
import ptidej.viewer.utils.Resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/06/29
 */
public final class GeneratePlantUMLWindow extends AbstractExternalWindow {
    private static final long serialVersionUID = 1L;


    public GeneratePlantUMLWindow() {
        super("Generate Plant UML");

        this.setResizable(true);
        this.setBounds(50, 50, 500, 500);
        DesktopPane.getInstance().setLayer(this, Resources.PROJECTS_LAYER);
        File myObj = new File("D:\\filename.txt");
        try {
            myObj.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void graphModelAvailable(
            final SourceAndGraphModelEvent aSourceModelEvent) {
        try {
            FileWriter myWriter = new FileWriter("D:\\filename.txt");
            PlantUMLVisitor walker = new PlantUMLVisitor();
            ptidej.ui.kernel.Entity[] entities = aSourceModelEvent.getRepresentation().getSourceGraph().listEntities();
            for (int i = 0; i < entities.length; i++){
                entities[i].getSourceEntity().accept(walker);

//                myWriter.write(entities[i].getName() + "\n");
            }
            myWriter.write(walker.getResult().toString());
            myWriter.close();
            this.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void graphModelUnavailable() {
        try {
            FileWriter myWriter = new FileWriter("D:\\filename.txt");
            myWriter.write("Model Unavailable");
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
