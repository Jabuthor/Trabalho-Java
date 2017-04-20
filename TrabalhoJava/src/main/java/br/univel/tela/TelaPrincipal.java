package br.univel.tela;
 
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
 
import br.univel.Coluna;
import br.univel.ConectarBanco;
import br.univel.ModeloTabela;
import br.univel.Pessoa;
import br.univel.Usql;
 
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
 
public class TelaPrincipal extends JFrame {
 
    private JPanel contentPane;
    private JTextField tfIdade;
    private JTextField tfPeso;
    private JTable table;
    /**
     * @wbp.nonvisual location=-143,409
     */
    private final JButton button = new JButton("New button");
    public JTextField tfNome;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal frame = new TelaPrincipal();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    public TelaPrincipal() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 670, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
       
        Pessoa p = new Pessoa();
       
        setContentPane(createScreen(p));
}
       

    public JPanel createScreen(Object o){
        
    	int i = 0;
       
        JPanel CPane = new JPanel();
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{0, 0, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        CPane.setLayout(gbl_contentPane);
       
        Class<?> c = o.getClass();
        for(Field f: c.getDeclaredFields()){
            JLabel newLbl = new JLabel(f.getAnnotation(Coluna.class).nome());
            CPane.add(newLbl,createConstraints(0, i));
            i++;
            JTextField txtField = new JTextField();
            txtField.setColumns(15);
            CPane.add(txtField,Constraints(0, i));
            i++;
        }
        JButton jb = new JButton("Adicionar");
        jb.setPreferredSize(new Dimension(130, 27));
        CPane.add(jb,btnCreateConstraints(0, i));
        
        JButton jb2 = new JButton("Excluir");
        jb2.setPreferredSize(new Dimension(130, 27));
        CPane.add(jb2,btnCreateConstraints(1, i));
        
        JButton jb3 = new JButton("Buscar Todos");
        jb3.setPreferredSize(new Dimension(130, 27));
        CPane.add(jb3,btnCreateConstraints(2, i));
        
        JButton jb4 = new JButton("Adicionar Tabela");
        jb4.setPreferredSize(new Dimension(130, 27));
        CPane.add(jb4,btnCreateConstraints(3, i));
        
        JButton jb5 = new JButton("Excluir Tabela");
        jb5.setPreferredSize(new Dimension(130, 27));
        CPane.add(jb5,btnCreateConstraints(4, i));

        JScrollPane jp = new JScrollPane();
        JTable tabela = new JTable();
        jp.setViewportView(tabela);
        
        return CPane;
    }
   
    private GridBagConstraints createConstraints(int x, int y){
       
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.gridx = x;
        gbc_textField.gridy = y;
       
        return gbc_textField;
    }
    private GridBagConstraints btnCreateConstraints(int x, int y){
        
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.anchor = GridBagConstraints.NORTH;
        gbc_textField.gridx = x;
        gbc_textField.gridy = y;
       
        return gbc_textField;
    }
    private GridBagConstraints Constraints(int x, int y){
    	
    	GridBagConstraints gbc_textField = new GridBagConstraints();
    	gbc_textField.gridwidth =2;
    	gbc_textField.gridx = x;
    	gbc_textField.gridy = y;
    	
    	return gbc_textField;
    }
 
}