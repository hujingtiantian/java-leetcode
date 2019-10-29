package github.leetcode.code;

import sun.reflect.Reflection;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class Main {

    // Default permission is allow
    public static final char DefaultPerm = '+';
    // Max valid URL length
    public static final int MaxValidUrlLength = 2047;








    public static void main(String[] args) throws IOException {



        System.err.println(System.currentTimeMillis());

        DomainFilter domainFilter = new DomainFilter();
        UrlPrefixFilter urlPrefixFilter = new UrlPrefixFilter();

        Future domainFuture = FilterThreadPool.loadFromFile(domainFilter,args[0]);
        Future urlFuture = FilterThreadPool.loadFromFile(urlPrefixFilter,args[1]);




        int count_of_allowed = 0;
        int count_of_disallowed = 0;
        int count_of_nohit = 0;
        int count_of_invalid = 0;
        long xor_of_allowed_value = 0;
        long xor_of_disallowed_value = 0;
        File file = new File(args[2]);
        FileInputStream fis = null;
        try {
            ReadFile readFile = new ReadFile();
            fis = new FileInputStream(file);
            int available = fis.available();
            int maxThreadNum = 50;
            // 线程粗略开始位置
            int i = available / maxThreadNum;
            for (int j = 0; j < maxThreadNum; j++) {
                // 计算精确开始位置
                long startNum = j == 0 ? 0 : readFile.getStartNum(file, i * j);
                long endNum = j + 1 < maxThreadNum ? readFile.getStartNum(file, i * (j + 1)) : -2;
                // 具体监听实现
                ReaderFileListener listeners = new ReaderFileListener();
                FilterThreadPool.readFile(listeners, startNum, endNum, file.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }



        try (BufferedReader f = new BufferedReader(new InputStreamReader(new FileInputStream(args[2])))) {

            try {
                domainFuture.get();
                urlFuture.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            int n = 0;
            for (String l : (Iterable<String>)f.lines()::iterator) {
                if (++ n % 10000 == 0) {
                    System.err.println("" + System.currentTimeMillis() + " processed: " + n);
                }
                if (l.isEmpty() || l.startsWith("#"))
                    continue;
                int i = l.indexOf('\t');
                String url = l.substring(0, i);
                String strValue = l.substring(i+1, i+9);
                long intValue = Long.parseLong(strValue, 16);

                if (url.length()>=MaxValidUrlLength) {
                    System.err.println("too-long-url:\t" + url + "\t" + strValue);
                    System.err.println("invalid-url:\t" + url + "\t" + strValue);
                    continue;
                }

                AttUrl u;
                try {
                    u = AttUrl.create(url);
                    if (u == null) {
                        System.err.println("invalid-url:\t" + url + "\t" + strValue);
                        continue;
                    } else {
                        if (u.port == 80 && u.scheme.equals("http"))
                            u = AttUrl.create(u.url.replace(":80", ""));
                        if (u.port == 443 && u.scheme.equals("https"))
                            u = AttUrl.create(u.url.replace(":443", ""));
                        if (u.path == null || u.path.isEmpty())
                            u = AttUrl.create(u.url.replace(u.domain, u.domain+"/"));
                        if (u.url.length() > MaxValidUrlLength) {
                            System.err.println("too-long-converted-url:\t" + u.url + "\t" + url + "\t" + strValue);
                            System.err.println("invalid-url:\t" + u.url + "\t" + url + "\t" + strValue);
                            continue;
                        }
                    }
                    char retcode;
                    retcode = domainFilter.filter(u);
//					String dbstr = "" + (retcode==0?'_':retcode);
                    if (retcode != '-') {
                        char oldretcode = retcode;
                        retcode = urlPrefixFilter.filter(u);
                        if (retcode == 0) {
                            retcode = oldretcode;
                        }
                        if (retcode == 0) {
                            count_of_nohit += 1;
                            retcode = Main.DefaultPerm;
                        }
                    }
//					dbstr += (retcode==0?'_':retcode);
//					System.err.println("R\t" + url + "\t" + dbstr);
                    if (retcode == '+') {
                        count_of_allowed += 1;
                        xor_of_allowed_value ^= intValue;
                        System.out.println(strValue);
                    } else if (retcode == '-') {
                        count_of_disallowed += 1;
                        xor_of_disallowed_value ^= intValue;
                    }
                } catch (MalformedURLException e) {
                    System.err.println("E\t" + url);
                }
            }
        }
        System.out.println(count_of_allowed);
        System.out.println(count_of_disallowed);
        System.out.println(count_of_nohit);
        System.out.format("%08x\n", xor_of_allowed_value);
        System.out.format("%08x\n", xor_of_disallowed_value);
        System.err.println(System.currentTimeMillis());
    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static class DomainFilter implements UrlFilter {
        Collection<DomainRule> rules = new ArrayList<DomainRule>();

        public void load(Iterable<String> lines)
        {
            for (String l : lines) {
                if (l.isEmpty() || l.startsWith("#"))
                    continue;
                DomainRule r = new DomainRule();
                int i = l.indexOf('\t');
                r.postfix = l.substring(0, i);
                r.perm = l.charAt(i+1);

                int k = r.postfix.indexOf(':');
                if (k != -1) {
                    r.port = Integer.parseInt(r.postfix.substring(k+1));
                    r.postfix = r.postfix.substring(0, k);
                }

                rules.add(r);
            }
        }

        public char filter(AttUrl uri) {
            DomainRule rule = (DomainRule)UrlFilter.filter(uri, this.rules);
            if (rule != null) {
                return rule.perm;
            } else {
                return 0;
            }
        }

    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static class AttUrl {
        final String scheme;
        final String domain;
        final int port;
        final String path;
        final String url;

        public AttUrl(String urlstr) {
            int p1, p2, p3;
            url = urlstr;
            p1 = urlstr.indexOf("://");
            p2 = urlstr.indexOf(":", p1+3);
            p3 = urlstr.indexOf("/", p1+3);
            this.scheme = urlstr.substring(0, p1);
            this.path = urlstr.substring(p3);
            if (p2 !=-1 && p2 < p3) {
                this.domain = urlstr.substring(p1+3, p2);
                this.port = Integer.parseInt(urlstr.substring(p2+1, p3));
            } else {
                this.domain = urlstr.substring(p1+3, p3);
                if (scheme.equals("http")) {
                    this.port = 80;
                } else if (scheme.equals("https")) {
                    this.port = 443;
                } else {
                    this.port = -1;
                }
            }
        }

        public static AttUrl create(String urlstr) throws MalformedURLException {
            return new AttUrl(urlstr);
        }
    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static class DomainRule implements Rule<DomainRule> {
        public String postfix;
        public int port = -1;
        public char perm;

        @Override
        public int compareTo(DomainRule o) {
            if (this.port !=-1 && o.port == -1) {
                return 1;
            } else if (this.port == -1 && o.port != -1) {
                return -1;
            } else if (this.postfix.length() > o.postfix.length()) {
                return 1;
            } else if (this.postfix.length() < o.postfix.length()) {
                return -1;
            } else if (this.perm == '-' && o.perm == '+') {
                return 1;
            } else if (this.perm == '+' && o.perm == '-') {
                return 1;
            }
            return this.postfix.compareTo(o.postfix);
        }

        @Override
        public boolean match(AttUrl u) {
            if (u.domain.endsWith(this.postfix)) {
                if (u.domain.length() != this.postfix.length()) {
                    if (this.postfix.charAt(0) != '.')
                        return false;
                }
                if (this.port == -1) {
                    return true;
                } else {
                    if (this.port == 80 && u.port == -1 && u.scheme.equals("http")) {
                        return true;
                    } else if (this.port == 443 && u.port == -1 && u.scheme.equals("https")) {
                        return true;
                    } else if (this.port == u.port) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static class FilterThreadPool {

        public static BlockingQueue workQueue = new ArrayBlockingQueue(16);

        public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 100, 20000, TimeUnit.SECONDS, workQueue, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        });

        public static Future loadFromFile(UrlFilter urlFilter,String fileName){
            return threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        urlFilter.loadFromFile(fileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        public static Future readFile(ReaderFileListener listeners, long startNum, long endNum, String path) {
            return threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    ReadFile readFile = new ReadFile();
                    readFile.setReaderListener(listeners);
                    readFile.setEncode(listeners.getEncode());
                    try {
                        readFile.readFileByLine(path, startNum, endNum + 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static class ReadFileCallable implements Callable<UrlFilter> {

        @Override
        public UrlFilter call() throws Exception {
            return null;
        }
    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static interface Rule<T> extends Comparable<T> {
        public boolean match(AttUrl u);
    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static interface UrlFilter {
        public void load(Iterable<String> lines);
        default public void loadFromFile(String filename) throws IOException
        {
            try (BufferedReader f = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
                this.load((Iterable<String>)f.lines()::iterator);
            }
        }

        static public Rule filter(AttUrl uri, Iterable<? extends Rule> rules) {
            Rule bestRule = null;
            for (Rule rule : rules) {
                if (rule.match(uri)) {
                    if (bestRule == null) {
                        bestRule = rule;
                    } else {
                        if (rule.compareTo(bestRule) > 0) {
                            bestRule = rule;
                        }
                    }
                }
            }
            return bestRule;

        }
        public char filter(AttUrl uri);

    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static class UrlPrefixFilter implements UrlFilter {
        Collection<UrlPrefixRule> rules = new ArrayList<UrlPrefixRule>();

        public void load(Iterable<String> lines)
        {
            for (String l : lines) {
                if (l.isEmpty() || l.startsWith("#"))
                    continue;
                UrlPrefixRule r = new UrlPrefixRule();
                int i = l.indexOf('\t');
                int j = l.indexOf('\t', i+1);
                r.prefix = l.substring(0, i);
                r.range = l.charAt(i+1);
                r.perm = l.charAt(j+1);

                if (r.prefix.startsWith("//")) {
                    UrlPrefixRule r1 = new UrlPrefixRule();
                    UrlPrefixRule r2 = new UrlPrefixRule();
                    r1.prefix = "http:" + r.prefix;
                    r1.range = r.range;
                    r1.perm = r.perm;
                    r2.prefix = "https:" + r.prefix;
                    r2.range = r.range;
                    r2.perm = r.perm;
                    rules.add(r1);
                    rules.add(r2);
                } else {
                    rules.add(r);
                }
            }
        }

        public char filter(AttUrl uri) {
            UrlPrefixRule rule = (UrlPrefixRule) UrlFilter.filter(uri, this.rules);
            if (rule != null) {
                return rule.perm;
            } else {
                return 0;
            }
        }
    }

    /**
     * Created by hujingtian on 2019/9/24.
     */
    public static class UrlPrefixRule implements Rule<UrlPrefixRule> {
        public String prefix;
        public char range; // "=+*"
        public char perm;

        @Override
        public int compareTo(UrlPrefixRule o) {
            if (this.range != o.range) {
                if (this.range == '=' && o.range != '=') {
                    return 1;
                } else if (this.range != '=' && o.range == '=') {
                    return -1;
                }
            }
            if (this.prefix.length() > o.prefix.length()) {
                return 1;
            } else if (this.prefix.length() < o.prefix.length()) {
                return -1;
            } else if (this.prefix.equals(o.prefix)) {
                if (this.range == o.range) {
                    if (this.perm == o.perm) {
                        return 0;
                    } else {
                        return this.perm == '-' ? 1 : -1;
                    }
                } else { // must be '+' and '*', no '='
                    return this.perm == '+' ? 1 : -1;
                }
            }
            return this.prefix.compareTo(o.prefix);
        }

        @Override
        public boolean match(AttUrl u) {

            if (this.range == '=') {
                return u.url.equals(this.prefix);
            } else if (this.range == '+') {
                return u.url.length() > this.prefix.length() && u.url.startsWith(this.prefix);
            } else if (this.range == '*') {
                return u.url.startsWith(this.prefix);
            }

            return false;
        }
    }
}

