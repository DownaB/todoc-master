package adapter;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;


import java.util.List;

public class TasksRecyclerViewAdapter extends RecyclerView.Adapter<TasksRecyclerViewAdapter.ViewHolder> {

    public static final class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView taskName;
        private final TextView projectName;
        private final ImageView imgProject;
        private final ImageView imgDelete;

        private final TasksRecyclerViewAdapter.DeleteTaskListener deleteTaskListener;

        TasksRecyclerViewAdapter(@NonNull final List<Task> tasks, @NonNull final TasksRecyclerViewAdapter.DeleteTaskListener deleteTaskListener) {
            this.tasks = tasks;
            this.deleteTaskListener = deleteTaskListener;
        }

        public ViewHolder(View view) {
            super(view);

            taskName = view.findViewById(R.id.lbl_task_name);
            projectName = view.findViewById(R.id.lbl_project_name);
            imgProject = view.findViewById(R.id.img_project);
            imgDelete = view.findViewById(R.id.img_delete);

            TaskViewHolder(@NonNull View view, @NonNull DeleteTaskListener deleteTaskListener) {
                super(view);

                this.deleteTaskListener = deleteTaskListener;

                imgDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Object tag = view.getTag();
                        if (tag instanceof Task) {
                            TasksRecyclerViewAdapter.TaskViewHolder.this.deleteTaskListener.onDeleteTask((Task) tag);
                        }
                    }
                });
            }
        }

        public void update(Task task) {
            taskName.setText(task.getName());
            imgDelete.setTag(task);

            final Project taskProject = task.getProject();
            if (taskProject != null) {
                imgProject.setSupportImageTintList(ColorStateList.valueOf(taskProject.getColor()));
                projectName.setText(taskProject.getName());
            } else {
                imgProject.setVisibility(View.INVISIBLE);
                projectName.setText("");
            }
        }
    }

    private final List<Task> tasks;

    public TasksRecyclerViewAdapter(List<Task> items){
        tasks = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Task task = tasks.get(position);
        holder.update(task);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface DeleteTaskListener {

        void onDeleteTask(Task task);
    }


}

