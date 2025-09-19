#!/usr/bin/env node
const fs = require('fs');
const path = require('path');

const srcDir = path.resolve(__dirname, '..', 'inspiration', 'cat-tastic-canvas-main', 'src', 'assets');
const dstDir = path.resolve(__dirname, '..', 'public', 'assets');

function ensureDir(p) {
  if (!fs.existsSync(p)) fs.mkdirSync(p, { recursive: true });
}

function copyIfExists() {
  if (!fs.existsSync(srcDir)) {
    console.log('[copy-assets] Source not found:', srcDir);
    return 0;
  }
  ensureDir(dstDir);
  const files = fs.readdirSync(srcDir).filter((f) => /\.(jpe?g|png|svg)$/i.test(f));
  let count = 0;
  for (const f of files) {
    fs.copyFileSync(path.join(srcDir, f), path.join(dstDir, f));
    count++;
  }
  console.log(`[copy-assets] Copied ${count} file(s) to ${dstDir}`);
  return count;
}

try {
  copyIfExists();
} catch (e) {
  console.error('[copy-assets] Failed:', e.message);
  process.exit(1);
}
