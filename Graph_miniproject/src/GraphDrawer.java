import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;

public class GraphDrawer {

    public static void drawGraph(Graph<String, Edge> graph) {
        // Set up the visualization viewer
        VisualizationViewer<String, Edge> vv = new VisualizationViewer<>(new CircleLayout<>(graph));
        vv.setPreferredSize(new Dimension(600, 600));

        // Add vertex labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());

        // Add edge weight labels
        Transformer<Edge, String> transformer = Edge::toString;
        vv.getRenderContext().setEdgeLabelTransformer(transformer);
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);

        // Create the frame and add the viewer to it
        JFrame frame = new JFrame("Job Matcher Graph");
        frame.getContentPane().add(vv);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
