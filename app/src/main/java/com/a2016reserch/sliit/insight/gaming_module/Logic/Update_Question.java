package com.a2016reserch.sliit.insight.gaming_module.Logic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Sandasala on 11/3/2016.
 */
public class Update_Question {



        @SerializedName("quzId")
        @Expose
        private Integer quzId;
        @SerializedName("question")
        @Expose
        private String question;
        @SerializedName("answer")
        @Expose
        private String answer;
        @SerializedName("level")
        @Expose
        private String level;
        @SerializedName("sequence")
        @Expose
        private Integer sequence;

        /**
         *
         * @return
         * The quzId
         */
        public Integer getQuzId() {
            return quzId;
        }

        /**
         *
         * @param quzId
         * The quzId
         */
        public void setQuzId(Integer quzId) {
            this.quzId = quzId;
        }

        /**
         *
         * @return
         * The question
         */
        public String getQuestion() {
            return question;
        }

        /**
         *
         * @param question
         * The question
         */
        public void setQuestion(String question) {
            this.question = question;
        }

        /**
         *
         * @return
         * The answer
         */
        public String getAnswer() {
            return answer;
        }

        /**
         *
         * @param answer
         * The answer
         */
        public void setAnswer(String answer) {
            this.answer = answer;
        }

        /**
         *
         * @return
         * The level
         */
        public String getLevel() {
            return level;
        }

        /**
         *
         * @param level
         * The level
         */
        public void setLevel(String level) {
            this.level = level;
        }

        /**
         *
         * @return
         * The sequence
         */
        public Integer getSequence() {
            return sequence;
        }

        /**
         *
         * @param sequence
         * The sequence
         */
        public void setSequence(Integer sequence) {
            this.sequence = sequence;
        }


}
