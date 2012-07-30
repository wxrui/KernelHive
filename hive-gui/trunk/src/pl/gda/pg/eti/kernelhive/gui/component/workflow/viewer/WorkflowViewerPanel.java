package pl.gda.pg.eti.kernelhive.gui.component.workflow.viewer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class WorkflowViewerPanel extends JPanel {

	private static final long serialVersionUID = -2548380853826858288L;
	private JTable table;
	private JButton btnRefresh;

	public WorkflowViewerPanel() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.SOUTH);

		btnRefresh = new JButton("Refresh");
		btnRefresh.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(btnRefresh);

		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		fillWorkflowExecutionsTable();
	}

	private void fillWorkflowExecutionsTable() {
		WorkflowExecutionsTableModel model = new WorkflowExecutionsTableModel();
		table.setModel(model);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		model.addTableModelListener(table);
	}
	
	public void addRefreshBtnActionListener(ActionListener l){
		btnRefresh.addActionListener(l);
	}
	
	public void removeRefreshBtnActionListener(ActionListener l){
		btnRefresh.removeActionListener(l);
	}
	
	public void refreshTableContents(){
		fillWorkflowExecutionsTable();
	}
	
	public void selectAllTableContents(){
		table.selectAll();
	}

	private class WorkflowExecutionsTableModel extends AbstractTableModel {

		private final int columnSize = 4;
		private final String[] columnNames = new String[] { "ID", "Name",
				"Status", "Results" };
		private final Class[] columnClasses = new Class[] { String.class,
				String.class, String.class, URL.class };
		List<Object[]> data = new ArrayList<Object[]>();

		public WorkflowExecutionsTableModel() {
			super();
		}

		@Override
		public int getRowCount() {
			return data.size();
		}

		@Override
		public int getColumnCount() {
			return columnSize;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex < columnSize && rowIndex < data.size()) {
				return data.get(rowIndex)[columnIndex];
			} else {
				return null;
			}
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex < columnSize) {
				return columnNames[columnIndex];
			} else {
				return null;
			}
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			if (columnIndex < columnSize) {
				return columnClasses[columnIndex];
			} else {
				return null;
			}
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			if (rowIndex < data.size() && columnIndex < columnSize) {
				data.get(rowIndex)[columnIndex] = aValue;
			}
			for (TableModelListener l : this.getTableModelListeners()) {
				l.tableChanged(new TableModelEvent(this, rowIndex, rowIndex,
						columnIndex, TableModelEvent.UPDATE));
			}
		}

	}

}