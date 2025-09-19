public class Money implements Award {

        public int rewardAmount;
        public int penaltyAmount;

        public money(int rewardAmount, int penaltyAmount) {
            this.rewardAmount = rewardAmount;
            this.penaltyAmount = penaltyAmount;
        }
        @Override
    public int displayWinnings(Players player, boolean isCorrect) {
        if (isCorrect) {
            System.out.println("Congratulations, " + player.getFirstName() + "! You are the winner!");
            return rewardAmount;
        } else {
            System.out.println("Sorry " + player.getFirstName() + ". You lost.");
            return penaltyAmount;
        }
    }
}