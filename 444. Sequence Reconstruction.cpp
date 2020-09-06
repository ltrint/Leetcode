class Solution {
public:
    bool sequenceReconstruction(vector<int>& org, vector<vector<int>>& seqs) {
        unordered_map<int, int> m, pre;
        for (int i = 0; i < org.size(); ++i) m[org[i]] = i;
        for (auto& seq : seqs) {
            for (int i = 0; i < seq.size(); ++i) {
                if (!m.count(seq[i])) return false;
                if (i > 0 && m[seq[i - 1]] >= m[seq[i]]) return false;
                if (!pre.count(seq[i])) {
                    pre[seq[i]] = (i > 0) ? m[seq[i - 1]] : -1;
                } else {
                    pre[seq[i]] = max(pre[seq[i]], (i > 0) ? m[seq[i - 1]] : -1);
                }
            }
        }
        for (int i = 0; i < org.size(); ++i) {
            if (pre[org[i]] != i - 1) return false;
        }
        return true;
    }
};
