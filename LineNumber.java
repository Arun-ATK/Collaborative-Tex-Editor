import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.Font;

public class LineNumber extends JTextArea {
    int curMaxLineNumber = 1;

    public LineNumber() {
        this.setPreferredSize(new Dimension(25, 700));
        this.setColumns(1);
        addNewLine();
        this.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    public void addNewLine() {
        this.append(String.format("%d\n", curMaxLineNumber));
        curMaxLineNumber++;
    }

    public void removeLine() {
        curMaxLineNumber--;
        String cur = this.getText();
        this.setText(cur.substring(0, cur.lastIndexOf("\n") - 1));
    }
}
