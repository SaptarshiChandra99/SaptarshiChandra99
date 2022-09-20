package Calculator_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorMine implements ActionListener {

    JTextField mainField,viewField;

    JButton[] numbers=new JButton[10];
    JButton[] functions=new JButton[9];
    JButton eqlButton,dltButton,clrButton,addButton,subButton,mulButton,divButton,dotButton,negButton;

    Font myfont=new Font("ariel",Font.BOLD,30);

    double num1,num2,res;
    char operator;
    boolean ifneg=false;

    public static void main(String[] args) {
        new CalculatorMine();
    }

    CalculatorMine(){
        JFrame frame=new JFrame();
        frame.setSize(450,600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        viewField=new JTextField();
        viewField.setFont(new Font("arial",Font.ITALIC,20));
        viewField.setEditable(false);
        viewField.setBounds(20,20,390,40);

        mainField=new JTextField();
        mainField.setFont(myfont);
        mainField.setEditable(false);
        mainField.setBounds(20,70,390,50);

        for(int i=0;i< numbers.length;i++){
            numbers[i]=new JButton(String.valueOf(i));
            numbers[i].setFont(myfont);
            numbers[i].addActionListener(this);
            numbers[i].setFocusable(false);
        }

        functions[0]=addButton=new JButton("+");
        functions[1]=subButton=new JButton("-");
        functions[2]=mulButton=new JButton("*");
        functions[3]=divButton=new JButton("/");
        functions[4]=clrButton=new JButton("C");
        functions[5]= dltButton=new JButton("D");
        functions[6]=eqlButton=new JButton("=");
        functions[7]=dotButton=new JButton(".");
        functions[8]=negButton=new JButton("(-)");

        for(int i=0;i< functions.length;i++){
            functions[i].setFocusable(false);
            functions[i].setFont(myfont);
            functions[i].addActionListener(this);
        }

        JPanel panel=new JPanel();
        //panel.setBackground(Color.black);
        panel.setBounds(20,140,390,300);
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setFont(myfont);


       panel.add(numbers[0]);
       panel.add(numbers[1]);
       panel.add(numbers[2]);
       panel.add(functions[0]);
       panel.add(numbers[3]);
       panel.add(numbers[4]);
       panel.add(numbers[5]);
       panel.add(functions[1]);
       panel.add(numbers[6]);
        panel.add(numbers[7]);
        panel.add(numbers[8]);
        panel.add(functions[2]);
        panel.add(numbers[9]);
        panel.add(dotButton);
        panel.add(negButton);
        panel.add(divButton);


        dltButton.setBounds(60,460,100,50);
        clrButton.setBounds(160,460,100,50);
        eqlButton.setBounds(260,460,100,50);


        frame.add(panel);
      //  frame.add(panel);
        frame.add(viewField);
        frame.add(mainField);
        frame.add(dltButton);
        frame.add(clrButton);
        frame.add(eqlButton);
        frame.setVisible(true);
    }
    public void setNum1(){
        if(ifneg){
           this.num1=Double.parseDouble(mainField.getText().substring(2,mainField.getText().length()-1))*-1;
        }
        else
            num1=Double.parseDouble(mainField.getText());
        ifneg=false;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i=0;i< numbers.length;i++){
            if(e.getSource()==numbers[i]){
                mainField.setText(mainField.getText().concat(Integer.toString(i)));
                viewField.setText(viewField.getText().concat(Integer.toString(i)));
            }
        }
        if(e.getSource()==addButton){
           // num1=Integer.parseInt(mainField.getText());
            setNum1();
            mainField.setText("");
            viewField.setText(viewField.getText()+" + ");
            operator='+';
        }
        if(e.getSource()==subButton){
           // num1=Integer.parseInt(mainField.getText());
            setNum1();
            mainField.setText("");
            viewField.setText(viewField.getText()+" - ");
            operator='-';
        }
        if(e.getSource()==mulButton){
         //   num1=Integer.parseInt(mainField.getText());
            setNum1();
            mainField.setText("");
            viewField.setText(viewField.getText()+" * ");
            operator='*';
        }
        if(e.getSource()==divButton){
            //num1=Integer.parseInt(mainField.getText());
            setNum1();
            mainField.setText("");
            viewField.setText(viewField.getText()+" / ");
            operator='/';
        }

        if(e.getSource()==eqlButton){
            if(ifneg){
                num2=Double.parseDouble(mainField.getText().substring(2,mainField.getText().length()-1))*-1;
            }
            else
                num2=Double.parseDouble(mainField.getText());
            ifneg=false;
            switch (operator){
                case '+':{
                    res=num1+num2;
                    mainField.setText(res+"");
                    viewField.setText(res+"");
                    break;
                }
                case '-':{
                    res=num1-num2;
                    mainField.setText(res+"");
                    viewField.setText(res+"");
                    break;
                }
                case '*':{
                    res=num1*num2;
                    mainField.setText(res+"");
                    viewField.setText(res+"");
                    break;
                }
                case '/':{
                    res=num1/num2;
                    mainField.setText(res+"");
                    viewField.setText(res+"");
                    break;
                }
                default:{
                    mainField.setText("Error");
                    viewField.setText("Error");
                }
            }
            num1=res;
        }

        if(e.getSource()==clrButton){
            mainField.setText("");
            viewField.setText("");
            operator='E';
            num2=0;num1=0;res=0;
        }
        if(e.getSource()==dltButton){
            mainField.setText(mainField.getText().substring(0,mainField.getText().length()-1));
            viewField.setText(viewField.getText().substring(0,viewField.getText().length()-1));

        }
        if(e.getSource()==dotButton){
            mainField.setText(mainField.getText()+".");
            viewField.setText(viewField.getText()+".");
        }
        if(e.getSource()==negButton && !(mainField.getText().equals(""))){
            ifneg=true;
            mainField.setText("(-"+mainField.getText()+")");
            String temp=viewField.getText();
            for(int i=temp.length()-1;i>=0;i--){
                if(i==0){
                    viewField.setText(mainField.getText());
                    break;
                }
                if(temp.charAt(i)=='.') continue;
                if(temp.charAt(i)<'0' || temp.charAt(i)>'9'){
                    viewField.setText(temp.substring(0,i)+mainField.getText());
                    break;
                }
            }
        }

    }
}
